package io.github.peterwhiffin.LibGDXGame;

import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;

public class Transform{
    public Vector3 position;
    public Quaternion rotation;
    public Vector3 scale;

    public Transform(){
        position =  new Vector3(0f, 0f, 0f);
        rotation = new Quaternion(0f, 0f, 0f, 1f);
        scale = new Vector3(1f, 1f, 1f);
    }

    public Vector3 forward(){
        return QuaternionByVector3(rotation, new Vector3(0f, 0f, 1f));
    }

    public Vector3 up(){
        return QuaternionByVector3(rotation, new Vector3(0f, 1f, 0f));
    }

    public Vector3 right(){
        return QuaternionByVector3(rotation, new Vector3(1f, 0f, 0f));
    }

    private Vector3 QuaternionByVector3(Quaternion rotation, Vector3 point){
        float num = rotation.x * 2f;
        float num2 = rotation.y * 2f;
        float num3 = rotation.z * 2f;
        float num4 = rotation.x * num;
        float num5 = rotation.y * num2;
        float num6 = rotation.z * num3;
        float num7 = rotation.x * num2;
        float num8 = rotation.x * num3;
        float num9 = rotation.y * num3;
        float num10 = rotation.w * num;
        float num11 = rotation.w * num2;
        float num12 = rotation.w * num3;
        Vector3 result = new Vector3(0f, 0f, 0f);
        result.x = (1f - (num5 + num6)) * point.x + (num7 - num12) * point.y + (num8 + num11) * point.z;
        result.y = (num7 + num12) * point.x + (1f - (num4 + num6)) * point.y + (num9 - num10) * point.z;
        result.z = (num8 - num11) * point.x + (num9 + num10) * point.y + (1f - (num4 + num5)) * point.z;
        return result;
    }
}
