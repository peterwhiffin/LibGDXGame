package io.github.peterwhiffin.LibGDXGame;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.ModelLoader;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Cubemap;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.loader.G3dModelLoader;
import com.badlogic.gdx.graphics.g3d.loader.ObjLoader;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.shaders.DefaultShader;
import com.badlogic.gdx.graphics.g3d.utils.AnimationController;
import com.badlogic.gdx.graphics.g3d.utils.DefaultShaderProvider;
import com.badlogic.gdx.graphics.g3d.utils.FirstPersonCameraController;
import com.badlogic.gdx.utils.JsonReader;

public class Core implements ApplicationListener {
    private World m_world;
    private Drawer m_drawer;
    private Input m_input;
    private PerspectiveCamera m_camera;
    public Model treeModel;
    public Model armsModel;
    public FirstPersonCameraController testController;
    public Application app;
    public AnimationController animationController;
    public AssetManager assetManager;
    public boolean isLoading;
    @Override
    public void create() {

        assetManager = new AssetManager();
        assetManager.load("FBX/G3DB/FPSArms.g3db", Model.class);
        isLoading = true;



    }

    public void DoneLoading(){

        isLoading = false;
        armsModel = assetManager.get("FBX/G3DB/FPSArms.g3db", Model.class);
        DefaultShader.Config shaderConfig = new DefaultShader.Config();
        shaderConfig.numBones = 34;


        ModelBatch modelBatch = new ModelBatch(new DefaultShaderProvider(shaderConfig));
        Environment environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
        environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));
        m_camera = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        m_camera.near = 0.01f;
        m_camera.far = 600f;
        m_camera.position.set(0f, 0f, 0f);
        m_camera.lookAt(0f, 0f, 10f);
        m_camera.update();
        //testController = new FirstPersonCameraController(m_camera);
        app = Gdx.app;




        ModelLoader loader = new ObjLoader();
        treeModel = loader.loadModel(Gdx.files.internal("SyntyNature/Source Files/OBJ/SM_Tree_Pine_Large_01.obj"));




        m_input = new Input();

        m_drawer = new Drawer(m_camera, modelBatch, environment);
        m_world = new World(m_input, m_camera, m_drawer, this);
        Gdx.input.setInputProcessor(m_input);
    }

    @Override
    public void render() {
        if(isLoading){

            if(assetManager.update()) {
                DoneLoading();
            }
            else{
                return;
            }
        }

        m_input.Update();
        m_world.Update();
        m_world.LateUpdate();
        m_input.LateUpdate();
        //testController.update();
        m_drawer.Draw();
        m_camera.update();
    }

    @Override
    public void dispose() {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }
}
