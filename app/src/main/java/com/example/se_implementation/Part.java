package com.example.se_implementation;

public class Part {
    private String id;
    private String name;
    private String category;
    private String producer;
    private String work_id;

    public Part(String id, String name, String category, String producer, String work_id) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.producer = producer;
        this.work_id = work_id;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getProducer() {
        return producer;
    }

    public String getWorkId() {
        return work_id;
    }

}
