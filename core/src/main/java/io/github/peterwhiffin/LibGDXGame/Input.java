package io.github.peterwhiffin.LibGDXGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Input.Keys;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Input extends InputAdapter {

    public Vector2 moveInput;
    public Vector2 lookInput;
    public boolean jumpInput;
    public boolean sprintInput;

    private Vector2 oldMouse;
    private Vector2 newMouse;
    private Vector2 temp;
    public Input(){
        Gdx.input.setCursorCatched(true);
        temp = new Vector2();
        newMouse = new Vector2();
        oldMouse = new Vector2();
        moveInput = new Vector2();
        lookInput = new Vector2();
        jumpInput = false;
        sprintInput = false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY){
        newMouse.x = screenX;
        newMouse.y = screenY;
        return true;
    }

    public Vector2 GetMouseDelta() {
        temp.set(lookInput);
        lookInput.set(0f, 0f);
        return temp;
    }

    @Override
    public boolean keyDown(int keycode) {
        switch(keycode){
            case Keys.W:
                moveInput.y += 1f;
                break;
            case Keys.S:
                moveInput.y -= 1f;
                break;
            case Keys.A:
                moveInput.x -= 1f;
                break;
            case Keys.D:
                moveInput.x += 1f;
                break;
            case Keys.SPACE:
                jumpInput = true;
                break;
            case Keys.SHIFT_LEFT:
                sprintInput = true;
                break;
        }

        moveInput.x = MathUtils.clamp(moveInput.x, -1f, 1f);
        moveInput.y = MathUtils.clamp(moveInput.y, -1f, 1f);
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch(keycode){
            case Keys.W:
                moveInput.y -= 1f;
                break;
            case Keys.S:
                moveInput.y += 1f;
                break;
            case Keys.A:
                moveInput.x += 1f;
                break;
            case Keys.D:
                moveInput.x -= 1f;
                break;
            case Keys.SPACE:
                jumpInput = true;
                break;
            case Keys.SHIFT_LEFT:
                sprintInput = true;
                break;
        }

        moveInput.x = MathUtils.clamp(moveInput.x, -1f, 1f);
        moveInput.y = MathUtils.clamp(moveInput.y, -1f, 1f);
        return true;
    }

    public void Update(){
        float deltaX = newMouse.x - oldMouse.x;
        float deltaY = newMouse.y - oldMouse.y;

        lookInput.x = deltaX;
        lookInput.y = deltaY;

        oldMouse.x = newMouse.x;
        oldMouse.y = newMouse.y;


    }

    public void LateUpdate(){
        //Gdx.input.setCursorPosition(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
    }

    public void Debug(){
        System.out.println(moveInput);
    }
}
