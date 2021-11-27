# US4071

=======================================


# 1. Requisitos

**US4071** Como Gestor de Projeto, eu pretendo que sejam desenvolvidos e integrados no Motor de Fluxos de Atividades algoritmos que distribuam a realização de tarefas automáticas pelas diversas instâncias do Executor de Tarefas Automáticas existentes.

- Como Gestor de Projeto quando os algoritmos forem desenvolvidos então pretendo que um seja baseado em First Came First Served (entre instâncias do Executor de Tarefas) e outro
  tenha em consideração a disponibilidade das respetivas instâncias e a carga atual das mesmas.

- Como Gestor de Projeto quando aceder ao sistema então pretendo que o algoritmo a ser adotado pelo sistema deve ser definido por configuração.

# 2. Análise

## 2.1. First-Came Fist-Served (FCFS)

O desenvolvimento desta funcionalidade seguiu o modelo clássico de produtor/consumidor, onde o produtor é o Motor do Fluxo de Atividades e os consumidores são as várias instâncias do Executor de Tarefas.

De modo geral:

1. O Motor de Fluxo de Atividades cria n threads de Executor de Atividades
2. As threads de Executor de Atividades são colocadas em espera passiva
3. É efetuado um Pedido
4. O Motor de Fluxo de Atividades obtém o Script da Base de Dados e adiciona-o a uma fila
5. As threads em espera são notificadas
6. As threads concorrem à execução do Script segundo uma estratégia First-Came Fist-Served (o primeiro a chegar, será o primeiro a ser atendido)



## 2.2. Algoritmo que tem em conta a carga e estado da instância

Este algoritmo funciona de forma similar ao FCFS. No entanto, este tem em conta as cargas de cada instância. Mais especificamente, primeiro verifica a instância que tem o menor número atual de execuções e atribuí a que tiver menor valor. Em caso de empate, o script é atribuído a qualquer uma das instâncias empatadas.

1. O Motor de Fluxo de Atividades cria n threads de Executor de Atividades
2. As threads de Executor de Atividades são colocadas em espera passiva
3. É efetuado um Pedido
4. O Motor de Fluxo de Atividades obtém o Script da Base de Dados e adiciona-o a uma fila
5. As threads em espera são notificadas
6. As threads concorrem à execução do Script, verificando a carga atual de todas as instâncias. Se a thread atual tiver atribuída à instância de menor carga, esta retira o script do topo da fila e inicia o seu processamento

# 3. Design

Este problema segue a linha do clássico produtor/consumidor,  onde o motor de fluxo é o produtor e as várias instâncias do executor os consumidores. Abaixo encontra-se uma explicação breve de cada classe  envolvida na resolução do problema.

- **MotorFluxoServer -**  Inicializa as threads do executor. Encontra-se no package base.daemon.motorfluxo.presentation.
- **BaseFluxRequest -** Faz a comunicação com a Base de Dados Remota para atualizar o estado da atividade. Encontra-se no package base.daemon.motorfluxo.algorithms.distribuicaoTarefasExecutor.
- **ExecutorHandler -** Representa a thread de executor. Implementa a interface Runnable. Encontra-se no package base.daemon.motorfluxo.algorithms.distribuicaoTarefasExecutor.
- **InitiateFluxRequestV1 -** Adiciona o Script à Script queue, aguarda o término da sua execução
- **BaseScriptQueue -**  Classe abstrata que permite gerir os scripts numa queue (adicionar/remover). Caso esta esteja vazia, as threads aguardam em espera passiva a adição de um script à queue. Quando um script é adicionado, as threads são notificadas e o script que está no topo da queue é removido. O método de remover é abstrato, sendo implementado pelas classes ScriptQueueFCFS e ScriptQueueLoadBased. Encontra-se no package base.daemon.motorfluxo.algorithms.distribuicaoTarefasExecutor.
- **ScriptQueueFCFS ** - extende de BaseScriptQueue. O método poll() segue a metodologia FCFS. Encontra-se no package base.daemon.motorfluxo.algorithms.distribuicaoTarefasExecutor.
- **ScriptQueueLoadBased ** - extende de BaseScriptQueue. O método poll() segue uma metodologia que tem em conta a carga atual das instâncias. Encontra-se no package base.daemon.motorfluxo.algorithms.distribuicaoTarefasExecutor.
- **ExecutorsThreadsData** - Classe que é responsável por inicializar as threads e gerir a informação relativa às várias instâncias do executor e seus respetivos scripts.




## 3.1. Pseudocódigo

### 3.1.1. sendScriptToExecutor() : Classe ManageFluxRequestV1

```pseudocode
BEGIN(PEDIDO ticket): Int
	ED: Int ticketId;//variável de instância da classe ManageFluxRequestV1
	String script := getScript(ticket);
	offerScriptToQueue(ticketId,script);//adicionar script à queue
	SEMAPHORE sem := getSemaphoreByTicketId(ticketId);//
	down(sem);//a thread correspondente dá release do semáforo quando obter resposta do executor
	response := removeData(ticketId);
	return response;	
END
```

### 3.1.2.offerScriptToQueue() : Classe ExecutorsThreadsData

```pseudocode
BEGIN(String ticketId, String script):
	ED: BaseScriptQueue q //variável da classe ExecutorsThreadsData
	q.offerScript(ticketId,script);
	return;
END
```

### 3.1.3. offerScript() : Classe BaseScriptQueue

````pseudocode
BEGIN(String ticketId, String script): Void
	scriptQueue.offer(ticketId, script);//Queue da instância
	ExecutorsThreadsData.putData(ticketId, 0);//associar um semáforo ao respetivo ticket com valor de 0
	notifiyAll();//notificar todas as threads
	return;
END
````

### 3.1.4. pollScript() : Classe ScriptQueueFCFS

```pseudocode
BEGIN SYNCHRONIZED(): Pair<String, String>
	while(queue is empty):
		wait();
	end while
	ticketIdScriptPair=queue.poll();
	notifyAll();//notificar todas as threads
	return ticketIdScriptPair;
END
```

### 3.1.5. pollScript() : Classe ScriptQueueLoadBased

````pseudocode
BEGIN SYNCHRONIZED(): Pair<String, String>
	//verificar se a thread conecta à instância menos ocupada
	Boolean isTurn=ExecutorsThreadsData.checkNextAvailableInstance();
	while(queue is empty || !isTurn):
		wait();
		//Quando acorda do wait, verifica se tem permissão para conectar à instância respetiva do motor
		isTurn=ExecutorsThreadsData.checkNextAvailableInstance();
	end while
	ticketIdScriptPair=queue.poll();
	notifyAll();//notificar todas as threads
	return ticketIdScriptPair;
END
````

### 3.1.5. run() : Classe ExecutorHandler

```pseudocode
BEGIN run(): Void
	while(true):
		handle();
	end while
END
```

### 3.1.6. handle() : Classe ExecutorHandler

```pseudocode
BEGIN handle(): Void
	//tentar obter script. Thread adormece enquanto não obter um script
	Pair<String,String> ticketIdScript=queue.pollScript();
	ExecutorsThreadsData.incrementConnectionPairCounter();//atualizar dados de uso das várias instâncias
    Semaphore sem=getSemaphore(ticketIdScript.left);//obter o semáforo respetivo ao ticket
    SDP2021Packet request=createRequest(ticketIdScript.right); //criar o pedido de ligação ao executor
    //mandar o script para a instância do executor e aguarda pela resposta
    SDP2021Packet executorResponse=sendRequestAndGetResponse(request);
    ExecutorsThreadsData.decrementConnectionPairCounter();//atualizar dados de uso das várias instâncias
    //adicionar resultado da execução à memória partilhada
    ExecutorsThreadsData.addTicketExecutionCode ( ticketId,executorResponse );
    //notificar a thread que iniciou a ligação ao motor de fluxo que pode ler a resposta da memória partilhada
    sem.release();
    return;
END
```

## 3.2. Padrões Aplicados

- **Factory**
- **Strategy**
- **Produtor/Consumidor**

## 3.3. Testes

1. Iniciar o Motor Fluxo Server executando o script run-engine-srv.sh
2. Iniciar pelo menos 1 instância do Executor de Atividades Server executando o script run-executor-srv.sh

**Teste 1:**  Permite iniciar uma thread de executor, executando o script associado ao pedido 2021/00001

```java
@Test
public void testeExecucaoScriptPeloMotor1() {
    String requestId="2021/00001";
    SDP2021Packet p=new SDP2021Packet ( version,scriptExecCode, requestId);
    try{
        SDP2021Packet result=SDP2021Client.simpleCommunication ( p,engineIp,enginePort, cert);
        System.out.println (result.response ());
    }catch (IOException e){
        System.out.println ("(testeExecucaoScriptPeloMotor1)Erro");
        e.printStackTrace ();
    }
}
```

**Teste 2:**  Permite iniciar `nScripts` threads de Executor, executando o script associados aos diferentes pedidos

```java
@Test
public void testeExecucaoScriptPeloMotor2(){
    int nScripts=5;
    String ticketPrefix="2021/00";
    SDP2021Packet[]packets=new SDP2021Packet[nScripts];
    for (int i = 0; i < nScripts; i++) {
        String ticket=ticketPrefix;
        if(i<9)
            ticket=ticket+"00"+(i+1);
        else
            ticket=ticket+"0"+(i+1);
        packets[i]=new SDP2021Packet ( version,scriptExecCode,ticket );
    }

    List<Thread> l=new ArrayList<> (nScripts);

    for (SDP2021Packet packet : packets) {
       l.add(new Thread (()->{
            try{
                SDP2021Packet result=SDP2021Client.simpleCommunication ( packet,engineIp,enginePort, cert );
                System.out.println ("packet: "+packet+":"+result.response ());
            }catch (IOException e){System.out.println ("Erro "+packet);}
        }));
    }
    l.forEach ( Thread::start );

    for (Thread thread : l) {
        try {
            thread.join ();
        } catch (InterruptedException e) {
            System.out.println ("Thread "+thread.getId ()+" interrupted");
        }
    }
}
```

# 4. Implementação

## Domain

Recorreu-se, nomeadamente, às classes de Pedido e relacionadas, uma vez que estas contêm informação necessária ao desenvolvimento do algoritmo (estado do Pedido, o script das atividades automáticas, etc.)

## Algorithms

Neste package estão contidas as classes onde se definiram os algoritmos, bem como a Factory que permite a criação do algoritmo pretendido, as classes representativas das Threads de Executor, a Queue de Scripts de Atividades Automáticas e a classe que armazena toda a informação necessária relativa às threads.

## Presentation

Destaca-se a classe MotorFluxoServer que tendo a responsabilidade de inicializar as várias threads de Executor tem um papel preponderante no desenvolvimento do algoritmo.

## 4.1. Análise de Performance Temporal

### 4.1.1. Procedimento Utilizado

Recorreu-se a um timer inicializado antes do conjunto de threads enviar os requests e terminado quando todas as threads efetuam join().

Este procedimento foi adotado para as seguintes circunstâncias:

**Tabela 1:** Cenários para análise da Performance Temporal

| Número de Threads ExecutorHandler | Número de Requests Concorrentes |
| :-------------------------------: | :-----------------------------: |
|                 4                 |            1,5,10,20            |
|                 8                 |            1,5,10,20            |
|                12                 |            1,5,10,20            |
|                16                 |            1,5,10,20            |


### 4.1.2. Resultados Obtidos

#### 4.1.2.1. First Came First Served

**Tabela 2:**  Tempo em segundos necessário para efetuar a execução com o  algoritmo  First Came First Served

| Nº Threads\Nº Requests |  1   |   5    |   10   |   20   |
| :--------------------: | :--: | :----: | :----: | :----: |
|           4            | 5.5s | 11.01s | 16.99s | 28.1s  |
|           8            | 5.6s | 5.78s  | 11.44s | 16.77s |
|           12           | 5.5s | 5.76s  | 11.21s | 11.78s |
|           16           | 5.3s | 5.63s  | 11.11s | 12.08s |

#### 4.1.2.1. Load Based

**Tabela 3:**  Tempo em segundos necessário para efetuar a execução com o  algoritmo  Load Based

| Nº Threads\Nº Requests |   1   |   5    |   10   |  20   |
| :--------------------: | :---: | :----: | :----: | :---: |
|           4            | 5.59s | 11.89s | 16.59s | 27.2s |
|           8            | 5.57s | 5.66s  | 11.13s | 12.4s |
|           12           | 5.59s |   6s   | 6.03s  | 9.51s |
|           16           | 5.47s | 5.71s  | 5.87s  | 9.35s |

### 4.1.3. Conclusão

De acordo com os resultados obtidos é possível constar que:

* Inicialmente, para um menor número de requests concorrentes, a diferença de performance entre os dois algoritmos é pouco significativa;
* Um maior número de requests concorrentes (e de threads de ExecutorHandler), no entanto, é mais eficiente para o algoritmo Load Based.


# 5. Integração/Demonstração

Uma vez que o Servidor do Motor de Fluxo se encontra numa máquina remota a deteção e tratamento de eventuais ocorrências de erros era bastante demorada, pelo que a integração foi um pouco complexa. Por forma a simplificar e automatizar ao máximo a tarefa de testagem e no sentido de integrar já outras funcionalidades garantidas pelo Motor de Fluxos (ex. o avanço no fluxo de atividades) desenvolveram-se os testes supramencionados que não só visam testar o comportamento das threads a executar diversos scripts(isto é, a distribuição dos scripts pelas threads de acordo com os critérios de cada algoritmo), como também permitem verificar a correta atualização dos estados do pedido e do fluxo.

# 6. Observações

A adoção de um possível mais eficiente método para escolher a instância consoante a carga.



