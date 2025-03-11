package io.github.peterwhiffin.LibGDXGame;

import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;

import java.util.ArrayList;
import java.util.List;

public class World {
    private List<Entity> m_entities;
    private CameraController m_cameraController;
    private Player player;
    private Drawer m_drawer;

    public World(Input input, PerspectiveCamera camera, Drawer drawer, Core core){
        Entity cameraTarget = new Entity();
        player = new Player(input, cameraTarget.transform);
        m_cameraController = new CameraController(camera, cameraTarget.transform);
        m_entities = new ArrayList<>();
        m_entities.add(player);
        m_entities.add(cameraTarget);

        Entity tree = new Entity();
        tree.m_model = new ModelInstance(core.treeModel);
        tree.transform.position = new Vector3(0f, 0f, -10f);
        m_entities.add(tree);
        m_drawer = drawer;
        m_drawer.AddModel(tree);
    }



    public void Update(){
        m_entities.forEach(Entity::Update);
    }

    public void LateUpdate(){
        m_cameraController.Update();
    }
}
