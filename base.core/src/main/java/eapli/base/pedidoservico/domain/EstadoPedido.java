package eapli.base.pedidoservico.domain;

public enum EstadoPedido {

    SUBMETIDO{
        @Override
        public String toString() {
            return "Submitted";
        }
    },
    EM_APROVACAO{
        @Override
        public String toString() {
            return "In Approval";
        }
    },
    APROVADO{
        @Override
        public String toString() {
            return "Approved";
        }
    },
    REJEITADO{
        @Override
        public String toString() {
            return "Rejected";
        }
    },
    PRONTO_EXECUTAR{
        @Override
        public String toString() {
            return "Ready To Execute";
        }
    },
    EM_RESOLUCAO{
        @Override
        public String toString() {
            return "In execution";
        }
    },
    CONCLUIDO{
        @Override
        public String toString() {
            return "Concluded";
        }
    }
}
