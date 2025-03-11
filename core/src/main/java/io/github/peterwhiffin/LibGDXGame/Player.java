package io.github.peterwhiffin.LibGDXGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.utils.AnimationController;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class Player extends Entity {
    private Input input;
    private float m_moveSpeed;
    private float m_lookPitch;
    private float m_sensitivity;
    public Transform m_cameraTarget;
    private Vector2 normalizedDirection;
    private Vector2 temp;
    private Entity arms;
    private AnimationController armsAnimator;

    public Player(Input input, Transform cameraTarget, ModelInstance armsInstance){
        temp = new Vector2();
        this.input = input;
        m_cameraTarget = cameraTarget;
        m_moveSpeed = 1f;
        m_sensitivity = .6f;
        normalizedDirection = new Vector2(0f, 0f);
        arms = new Entity();
        arms.m_model = armsInstance;
        armsAnimator = new AnimationController(armsInstance);
        armsAnimator.setAnimation("Armature|Armature|Stretch|Layer0", -1);
    }

    @Override
    public void Update() {

        armsAnimator.update(Gdx.graphics.getDeltaTime());
        temp.set(input.GetMouseDelta());
        normalizedDirection.set(0f, 0f);
        if(input.moveInput.x != 0 || input.moveInput.y != 0) {
            normalizedDirection.set(input.moveInput).nor();
        }
        else{
            normalizedDirection.set(0f, 0f);
        }



        transform.position.mulAdd(transform.forward(), normalizedDirection.y * m_moveSpeed);
        transform.position.mulAdd(transform.right(), -normalizedDirection.x * m_moveSpeed);

        float currentYaw = transform.rotation.getYaw();
        currentYaw -= temp.x * m_sensitivity;
        m_lookPitch += temp.y * m_sensitivity;

        m_lookPitch = MathUtils.clamp(m_lookPitch, -90f, 90f);
        transform.rotation.setEulerAngles(currentYaw, 0f, 0f);
        m_cameraTarget.position = transform.position;
        m_cameraTarget.rotation.setEulerAngles(transform.rotation.getYaw(), m_lookPitch, transform.rotation.getRoll());

        super.Update();
    }

    @Override
    public void LateUpdate() {
        super.LateUpdate();
    }
}
