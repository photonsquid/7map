package com.sevenmap.spinel.utils;

import java.awt.FileDialog;
import java.io.File;
import java.io.FilenameFilter;

public class FileChooser implements Runnable {
    private Thread fileChooserThread;

    private String targetDir;
    private String filePattern;
    private String file;
    private boolean isClosed = false;

    public FileChooser(String filePattern) {
        this.filePattern = filePattern;
        fileChooserThread = new Thread(this, this.getClass().getSimpleName());
        fileChooserThread.start();
    }

    public void run() {
        FileDialog fd = new java.awt.FileDialog((java.awt.Frame) null);
        if (filePattern != null)
            fd.setFile(filePattern);
        if (targetDir != null)
            fd.setDirectory(targetDir);
        fd.setVisible(true);
        fd.setFilenameFilter(new FilenameFilter() {

            @Override
            public boolean accept(File dir, String name) {
                return name.matches(filePattern);
            }

        });
        if (fd.getDirectory() == null || fd.getFile() == null) {
            file = null;
        } else {
            file = fd.getDirectory() + fd.getFile();
        }
        isClosed = true;
        fileChooserThread.interrupt();
    }

    public String getTargetDir() {
        return targetDir;
    }

    public void setTargetDir(String targetDir) {
        this.targetDir = targetDir;
    }

    public String getFilePattern() {
        return filePattern;
    }

    public void setFilePattern(String filePattern) {
        this.filePattern = filePattern;
    }

    public String getFilePath() {
        return file;
    }

    /**
     * Retrieve the FileChooser's state.
     * 
     * @return true if the file chooser has finished its job
     */
    public boolean isDone() {
        return isClosed;
    }

    public Thread getThread() {
        return fileChooserThread;
    }
}
