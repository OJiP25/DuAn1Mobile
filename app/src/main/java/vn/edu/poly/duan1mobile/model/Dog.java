package vn.edu.poly.duan1mobile.model;

public class Dog {

        String edtNamepet;
        String edtCharacteristics;
        String edtPrice;
        String edtLink;

        public Dog(){

        }

        public Dog(String edtNamepet, String edtCharacteristics, String edtPrice, String edtLink) {
            this.edtNamepet = edtNamepet;
            this.edtCharacteristics = edtCharacteristics;
            this.edtPrice = edtPrice;
            this.edtLink = edtLink;
        }

        public String getEdtNamepet() {
            return edtNamepet;
        }

        public void setEdtNamepet(String edtNamepet) {
            this.edtNamepet = edtNamepet;
        }

        public String getEdtCharacteristics() {
            return edtCharacteristics;
        }

        public void setEdtCharacteristics(String edtCharacteristics) {
            this.edtCharacteristics = edtCharacteristics;
        }

        public String getEdtPrice() {
            return edtPrice;
        }

        public void setEdtPrice(String edtPrice) {
            this.edtPrice = edtPrice;
        }

        public String getEdtLink() {
            return edtLink;
        }

        public void setEdtLink(String edtLink) {
            this.edtLink = edtLink;
        }


}
