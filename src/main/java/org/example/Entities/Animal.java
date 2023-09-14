package org.example.Entities;

public class Animal {
    int animalId;
    String animalName;
    String animalVoice;
    String hungryState;

    public int getAnimalId() {
        return animalId;
    }

    public void setAnimalId(int animalId) {
        this.animalId = animalId;
    }

    public String getHungryState() {
        return hungryState;
    }

    public void setHungryState(String hungryState) {
        this.hungryState = hungryState;
    }

    public Animal(String animalName, String animalVoice, String hungryState) {
        this.animalName = animalName;
        this.animalVoice = animalVoice;
        this.hungryState = hungryState;
    }

    public void MakeNoise(){
        System.out.println(animalName+" ses çıkarıyor "+ animalVoice);
    }
    public String getAnimalName() {
        return animalName;
    }
    public void setAnimalName(String animalName) {
        this.animalName = animalName;
    }
    public String getAnimalVoice() {
        return animalVoice;
    }
    public void setAnimalVoice(String animalVoice) {
        this.animalVoice = animalVoice;
    }
}
