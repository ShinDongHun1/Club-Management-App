package com.example.memberservice.global.aop.log;

/**
 * Created by ShinD on 2022-03-15.
 */

import java.util.UUID;


public class TraceId {

    private String id;
    private int level;//깊이


    public TraceId(String id) {
        this.id = createId(id);
        this.level = 0;
    }

    public TraceId(String id, int level) {
        this.id = id;
        this.level = level;
    }



    private String createId(String id) {
        return (id == null) ?
                String.format("Anonymous: %S",UUID.randomUUID().toString().substring(0,8))
                : id;
    }

    public TraceId createNextId(){
        return new TraceId(id, level + 1);
    }

    public TraceId createPreviousId(){
        return new TraceId(id, level - 1);
    }

    public boolean isFirstLevel(){
        return level == 0;
    }

    public String getId() {
        return id;
    }

    public int getLevel() {
        return level;
    }
}
