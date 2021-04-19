package com.sevenmap.ui.elements;

import static org.junit.Assert.assertEquals;

import com.sevenmap.TestFile;
import com.sevenmap.ui.gfx.Mesh;
import com.sevenmap.ui.math.Vector3f;

import org.junit.Test;

public class ItemTest extends TestFile{
 
  public static void verifyConstructor(Item i){
      
      Vector3f pos = i.getPos();
      Vector3f rot = i.getRot();
      Vector3f sca = i.getScale();
      assertEquals("this is a good constructor-pos-", i.position, pos);
      assertEquals("this is a good constructor-rot-", i.rotation, rot);
      assertEquals("this is a good constructor-sca-", i.scale, sca);
      
      
  }

  public static void VerifySet(Item i, float x, float y, float z){
      i.setPos(x,y,z);
      i.setRot(y,x,z);
      i.setScale(z,x,y);
      assertEquals( i.position, new Vector3f(x,y,z));
      assertEquals( i.rotation, new Vector3f(y,x,z));
      assertEquals( i.scale, new Vector3f(z,x,y));
  }

    @Test
    public static void main(String[] args) {
        float a,b,c;
        a= 2.4f;
        b= 3.5f;
        c= 2.9f;
        Vector3f v1 = new Vector3f(a, b, b);
        Vector3f v2 = new Vector3f(c,c,a);
        Vector3f v3 = new Vector3f(a,c,b);
        Mesh m = new Mesh();
        Item i = new Item(v1,v2,v3,m);
        verifyConstructor(i);
        VerifySet(i, 0.4f, 0.4f, 56f);

    }
}
