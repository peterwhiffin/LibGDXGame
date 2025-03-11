package io.github.peterwhiffin.LibGDXGame;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import java.util.ArrayList;
import java.util.List;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;

public class Drawer {

    private PerspectiveCamera m_camera;
    private List<ModelInstance> models;

    private ModelBatch modelBatch;
    private Environment environment;
    private Model boxModel;
    private ModelInstance instance;
    public Drawer(PerspectiveCamera camera, ModelBatch modelBatch, Environment environment){
        m_camera = camera;
        models = new ArrayList<>();
        this.modelBatch = modelBatch;
        this.environment = environment;

        ModelBuilder modelBuilder = new ModelBuilder();
        boxModel = modelBuilder.createBox(5f, 5f, 5f,
            new Material(ColorAttribute.createDiffuse(Color.GREEN)),
            Usage.Position | Usage.Normal);

        instance = new ModelInstance(boxModel);
        instance.transform.set(new Vector3(0f, 0f, 10f), new Quaternion(0, 0, 0, 1));
    }

    public void AddModel(ModelInstance model){
        models.add(model);
    }

    public void Draw(){


        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        modelBatch.begin(m_camera);
        modelBatch.render(models, environment);
        modelBatch.render(instance);
        modelBatch.end();
    }
}
