package com.jordifierro.androidbase.domain.entity;

/**
 * Created by jordifierro on 18/12/16.
 */

public class VoidEntity {

    private static VoidEntity voidEntity = new VoidEntity();

    private VoidEntity() {

    }

    public static VoidEntity getInstance() {
        return voidEntity;
    }
}
