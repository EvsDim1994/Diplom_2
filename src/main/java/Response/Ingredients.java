package Response;

import java.util.List;

public class Ingredients {
    private boolean success;
    private List<Item> data;

    // Геттеры и сеттеры
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<Item> getData() {
        return data;
    }

    public void setData(List<Item> data) {
        this.data = data;
    }

    public static class Item {
        private String _id;
        private String name;
        private String type;
        private int proteins;
        private int fat;
        private int carbohydrates;
        private int calories;
        private int price;
        private String image;
        private String image_mobile;
        private String image_large;
        private int __v;

        public String getId() {
            return _id;
        }

        public void setId(String id) {
            this._id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getProteins() {
            return proteins;
        }

        public void setProteins(int proteins) {
            this.proteins = proteins;
        }

        public int getFat() {
            return fat;
        }

        public void setFat(int fat) {
            this.fat = fat;
        }

        public int getCarbohydrates() {
            return carbohydrates;
        }

        public void setCarbohydrates(int carbohydrates) {
            this.carbohydrates = carbohydrates;
        }

        public int getCalories() {
            return calories;
        }

        public void setCalories(int calories) {
            this.calories = calories;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getImageMobile() {
            return image_mobile;
        }

        public void setImageMobile(String image_mobile) {
            this.image_mobile = image_mobile;
        }

        public String getImageLarge() {
            return image_large;
        }

        public void setImageLarge(String image_large) {
            this.image_large = image_large;
        }

        public int getV() {
            return __v;
        }

        public void setV(int v) {
            this.__v = v;
        }
    }
}
