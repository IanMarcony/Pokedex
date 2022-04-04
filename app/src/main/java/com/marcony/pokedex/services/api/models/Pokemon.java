package com.marcony.pokedex.services.api.models;

public class Pokemon {
    private int id;
    private String name;
    private Sprite  sprites;

    public class Sprite{
        private String front_default;

        public Sprite() {
        }

        public Sprite(String front_default) {
            this.front_default = front_default;
        }

        public String getFront_default() {
            return front_default;
        }

        public void setFront_default(String front_default) {
            this.front_default = front_default;
        }

        @Override
        public String toString() {
            return "Sprite{" +
                    "front_default='" + front_default + '\'' +
                    '}';
        }
    }

    public Pokemon() {
    }

    public Pokemon(int id, String name, Sprite sprites) {
        this.id = id;
        this.name = name;
        this.sprites = sprites;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Sprite getSprites() {
        return sprites;
    }

    public void setSprites(Sprite sprites) {
        this.sprites = sprites;
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sprites=" + sprites +
                '}';
    }
}
