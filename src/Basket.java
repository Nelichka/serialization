import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Basket {
    private int[] prices;
    private String[] products;
    private int[] totalBasket;
    private int sum;
    private boolean[] isFilled;


    public Basket(int[] prices, String[] products) {
        this.prices = prices;
        this.products = products;
        this.totalBasket = new int[products.length];
        this.isFilled = new boolean[products.length];
    }

    public Basket(int[] prices, String[] products, int[] totalBasket, boolean[] isFilled) {
        this.prices = prices;
        this.products = products;
        this.totalBasket = totalBasket;
        this.isFilled = isFilled;
    }


    public void addToCart(int productNum, int amount) {
        totalBasket[productNum] += amount;

        isFilled[productNum] = true;
    }

    public void printCart() {
        for (int i = 0; i < products.length; i++) {
            if (isFilled[i]) {
                System.out.println("Вы выбрали: " + products[i] + ", " + totalBasket[i] + " шт.; " +
                        "цена = " + prices[i] * totalBasket[i] + " руб.");
                sum += prices[i] * totalBasket[i];
            }
        }
        System.out.println("Общая стоимость = " + sum + " руб.");
    }

    public void saveTxt(File textFile) throws IOException {
        textFile = new File(textFile.toURI());
        try (PrintWriter out = new PrintWriter(textFile)) {
            for (int i = 0; i < totalBasket.length; i++) {

                out.print(totalBasket[i] + " ");
            }
            out.println();
            for (int j = 0; j < totalBasket.length; j++) {
                out.print(products[j] + " ");
            }
            out.println();
            for (int x = 0; x < prices.length; x++) {
                out.print(prices[x] + " ");
            }
            out.println();
            for (int y = 0; y < isFilled.length; y++) {
                out.print(isFilled[y] + " ");
            }
        }
    }

    public static Basket loadFromTxtFile(File textFile) throws IOException {

        String[] productsInBasket;
        String[] productNames;
        String[] pricesInBasket;
        String[] isProduct;

        try (BufferedReader reader = new BufferedReader(new FileReader(textFile))) {
            if (textFile.exists()) {
                productsInBasket = reader.readLine().split(" ");
                productNames = reader.readLine().split(" ");
                pricesInBasket = reader.readLine().split(" ");
                int[] pricesInt = new int[pricesInBasket.length];
                for (int i = 0; i < pricesInBasket.length; i++) {
                    pricesInt[i] = Integer.parseInt(pricesInBasket[i]);
                }
                Basket basket = new Basket(pricesInt, productNames);
                for (int i = 0; i < productsInBasket.length; i++) {
                    basket.totalBasket[i] = Integer.parseInt(productsInBasket[i]);
                }

                for (int i = 0; i < productNames.length; i++) {
                    basket.products[i] = productNames[i];
                }

                isProduct = reader.readLine().split(" ");
                for (int i = 0; i < isProduct.length; i++) {
                    basket.isFilled[i] = Boolean.parseBoolean(isProduct[i]);
                }
                return basket;
            } else {
                return new Basket(null, null, null, null);
            }
        }
    }
}
