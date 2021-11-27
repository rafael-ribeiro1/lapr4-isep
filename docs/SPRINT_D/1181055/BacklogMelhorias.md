# Melhorias nas User Stories
=======================================
Sprint C
-Refactor das classes FluxRequest e InitiateFluxRequestV0
-Correção do erro na conexão entre o motor de fluxo e o executor de tarefas
-Correção do optimistic lock aquando da definição do rascunho de um serviço, recorrendo ao reload da instância pela base de dados
-Transferência de alguma lógica que estava na classe SpecifyServiceUIControllerFacade para as UIs de addKeyword e removeKeyword
-Adição da exceção ConcurrencyException nas UIs que ainda não possuíam
-Soft delete do ServiceDraft quando este é convertido em Service
-Adição de uma classe própria para o bootstrap de Service