package Response;

import java.util.Date;
import java.util.List;

public class Orders {
    private boolean success;
    private List<Positions> orders;
    private int total;
    private int totalToday;

    public boolean isSuccess() {
        return success;
    }

    public List<Positions> getOrders() {
        return orders;
    }

    public int getTotal() {
        return total;
    }

    public int getTotalToday() {
        return totalToday;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setOrders(List<Positions> orders) {
        this.orders = orders;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setTotalToday(int totalToday) {
        this.totalToday = totalToday;
    }

    public static class Positions {
        private String id;
        private List<String> ingredients;
        private String status;
        private String name;
        private Date createdAt;
        private Date updatedAt;
        private int number;

        public String getId() {
            return id;
        }

        public List<String> getIngredients() {
            return ingredients;
        }

        public String getStatus() {
            return status;
        }

        public String getName() {
            return name;
        }

        public Date getCreatedAt() {
            return createdAt;
        }

        public Date getUpdatedAt() {
            return updatedAt;
        }

        public int getNumber() {
            return number;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setIngredients(List<String> ingredients) {
            this.ingredients = ingredients;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setCreatedAt(Date createdAt) {
            this.createdAt = createdAt;
        }

        public void setUpdatedAt(Date updatedAt) {
            this.updatedAt = updatedAt;
        }

        public void setNumber(int number) {
            this.number = number;
        }
    }
}