package model;

public class Sock {
   private int cottonPart;
   private int quantity;
private Size size;
private Color color;

    enum Size {S, M, L, XL, XXL, XXXL}

    enum Color {Black, White, Blue, Grey, Brown, Ocher}

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
}
