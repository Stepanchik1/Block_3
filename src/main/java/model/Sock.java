package model;

public class Sock {
   private int cottonPart;
   private int quantity;
private Size size;
private Color color;

    public enum Size {S ("S"), M ("M"), L ("L"), XL ("XL"), XXL ("XXL"), XXXL ("XXXL");
        String size;

        Size(String size) {
            this.size = size;
        }
    }

    public enum Color {Black ("черный"), White ("белый"), Blue ("синий"), Grey ("серый"), Brown ("коричневый"), Ocher ("охра");
        String color;

        Color(String color) {
            this.color = color;
        }

        @Override
        public String toString() {
            return this.color;
        }
    }

    public Sock(int cottonPart, int quantity, Size size, Color color) {
        this.cottonPart = cottonPart;
        this.quantity = quantity;
        this.size = size;
        this.color = color;
    }

    public int getCottonPart() {
        return cottonPart;
    }

    public int getQuantity() {
        return quantity;
    }

    public Size getSize() {
        return size;
    }

    public Color getColor() {
        return color;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    @Override
    public String toString() {
        return
                "\nсодержание хлопка - " + cottonPart +
                "%, \n" +
                "размер - " + size +
                "\nцвет - " + color +
                "\nколичество - "+ quantity + "шт.";
    }
}
