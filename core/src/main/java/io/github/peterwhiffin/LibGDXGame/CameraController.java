package io.github.peterwhiffin.LibGDXGame;

import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.utils.FirstPersonCameraController;

public class CameraController{
    private Transform m_cameraTarget;
    private PerspectiveCamera m_camera;

    public CameraController(PerspectiveCamera camera, Transform cameraTarget){
        m_cameraTarget = cameraTarget;
        m_camera = camera;
    }

    public void Update(){
        m_camera.position.set(m_cameraTarget.position);
        m_camera.direction.set(m_cameraTarget.forward());

    }
}
