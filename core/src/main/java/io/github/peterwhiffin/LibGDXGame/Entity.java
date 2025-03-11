package io.github.peterwhiffin.LibGDXGame;

import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;

public class Entity {
    public Transform transform;
    public ModelInstance m_model;

    public Entity(){
        transform = new Transform();
    }

    public void Update(){

        if(m_model != null) {

            m_model.transform.set(transform.position, transform.rotation, transform.scale);
        }
    }

    public void LateUpdate(){

    }
}
