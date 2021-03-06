package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.mygdx.game.scene.GameScene;
import com.mygdx.game.scene.Loading;
import com.mygdx.game.scene.LoginScene;
import com.mygdx.game.ui.SystemMessage;
import network.Network;

/**
 * Created by Lee on 2016-05-18.
 */
public class Client extends ApplicationAdapter {
    private static Client instance;
    public Client() { Client.instance = this; }

    private GameScene currentScene;
    private GameScene preScene;

    public static void changeCurrentScene(GameScene scene) {
        instance.currentScene = scene;
    }


    @Override
    public void render() {
        Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (this.preScene != this.currentScene) {
            if (this.preScene != null) this.preScene.dispose();
            if (this.currentScene != null) this.currentScene.create();
            this.preScene = this.currentScene;
        }
        if (this.currentScene != null) this.currentScene.render();
        SystemMessage.getInstance().render();
    }


    public static GameScene getCurrentScene() {
        return instance != null? instance.currentScene : null;
    }

    @Override
    public void create() {
        this.currentScene = new Loading(new LoginScene(), "초기화 중입니다.");
        try {
            Network.getInstance().connect();
        } catch (InterruptedException e) {
            e.printStackTrace();
            Gdx.app.exit();
        }
    }

    @Override
    public void dispose() {
        Network.getInstance().disconnect();
        if (this.currentScene != null) this.currentScene.dispose();
    }
}
