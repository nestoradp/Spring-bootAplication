package cu.curso.estudio.Entity;



public class ValidatorError {


        private String field;
        private  String message;

        public ValidatorError(String field, String message) {
            super();
            this.field = field;
            this.message = message;
        }


    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }


    public void setField(String field) {
        this.field = field;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}


