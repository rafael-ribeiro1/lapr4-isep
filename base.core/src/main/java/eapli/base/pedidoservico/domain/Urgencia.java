package eapli.base.pedidoservico.domain;

public enum Urgencia {

    REDUZIDO {
        @Override
        public String toString() {
            return "Reduced";
        }
    },
    MODERADO {
        @Override
        public String toString() {
            return "Moderate";
        }
    },
    URGENTE {
        @Override
        public String toString() {
            return "Urgent";
        }
    };

}
