<h1 align="center">📦 OrderFlow</h1>
<p align="center">Sistema de gerenciamento de pedidos com arquitetura de microserviços escalável e resiliente</p>

<p align="center">
  <img src="https://img.shields.io/badge/status-Em%20desenvolvimento-yellow" alt="Status: Em Desenvolvimento" />
  <img src="https://img.shields.io/badge/arquitetura-Microservi%C3%A7os-blue" alt="Arquitetura: Microserviços" />
  <img src="https://img.shields.io/badge/resili%C3%AAncia-Resilience4J-orange" alt="Resiliência: Resilience4J" />
  <img src="https://img.shields.io/badge/observabilidade-Prometheus%20%26%20Grafana-red" alt="Observabilidade: Prometheus & Grafana" />
  <img src="https://img.shields.io/badge/licen%C3%A7a-MIT-green" alt="Licença: MIT" />
</p>

---

<h2>✨ Visão Geral</h2>

<p>
  <strong>OrderFlow</strong> é uma aplicação de gerenciamento de pedidos desenvolvida com foco em **escalabilidade**, **organização**, **desacoplamento** e **resiliência**. A arquitetura baseia-se em **microserviços** que se comunicam de forma síncrona (REST) e assíncrona (mensageria), garantindo um fluxo de trabalho robusto e eficiente desde a criação do pedido até a notificação do usuário. Este projeto visa demonstrar a aplicação de boas práticas e padrões arquiteturais em um ambiente distribuído.
</p>

---

<h2>📐 Arquitetura do Sistema</h2>

<p>
  O OrderFlow é composto pelos seguintes microserviços, cada um com sua responsabilidade bem definida e comunicando-se de forma eficiente para orquestrar o fluxo de pedidos:
</p>

<ul>
  <li><strong>OrderMS</strong>: Responsável pela lógica central de pedidos, incluindo o gerenciamento do carrinho de compras e o ciclo de vida dos pedidos. Inicia o processo de pagamento e interage com o Inventário.</li>
  <li><strong>InventoryMS</strong>: Gerencia o catálogo de produtos e o controle de estoque. Atualiza o estoque em tempo real e emite eventos para o OrderMS.</li>
  <li><strong>PaymentMS</strong>: Processa todas as transações de pagamento. Lida com integrações de gateway de pagamento e garante a resiliência em caso de falhas, com retry e fallback.</li>
  <li><strong>NotificationMS</strong>: (Sugiro renomear de UsuarioNotificationsMS para algo mais genérico como NotificationMS) Responsável pelo envio de notificações (e-mail, SMS, push) aos usuários em diversas etapas do pedido e pagamento.</li>
  <li><strong>AuthMS</strong>: Gerencia a autenticação de usuários e a autorização de acesso aos recursos dos microserviços via JWT/OAuth2.</li>
  <li><strong>ReportMS</strong>: Encarregado da geração de relatórios analíticos e operacionais com base nos dados consolidados do sistema.</li>
  <li><strong>ScheduleMS</strong>: Gerencia a execução de tarefas agendadas, como envio de lembretes ou gatilho para a geração de relatórios periódicos.</li>
</ul>

**Diagrama de Arquitetura:**
<p align="center">
  <img src="docs/arquitetura_orderflow.png" alt="Diagrama de Arquitetura OrderFlow" width="800"/>
  <br>
  <em>Um diagrama visual da arquitetura de microserviços e suas interações.</em>
</p>

---

<h2>🛠️ Tecnologias Chave & Ecossistema</h2>

<p>Este projeto foi construído utilizando um conjunto de tecnologias modernas para garantir robustez, escalabilidade e manutenibilidade:</p>

<ul>
  <li>**Linguagem e Framework:** Java 17+ & Spring Boot 3.x (com Spring Cloud para ecossistema de microserviços)</li>
  <li>**Banco de Dados:** PostgreSQL (Persistência de dados transacionais)</li>
  <li>**Mensageria:** RabbitMQ (para comunicação assíncrona baseada em eventos, garantindo desacoplamento e resiliência)</li>
  <li>**Containerização & Orquestração:** Docker & Docker Compose (para empacotamento e orquestração dos serviços em ambiente de desenvolvimento)</li>
  <li>**Descoberta de Serviços:** Spring Cloud Eureka (Registro e descoberta de instâncias de microserviços)</li>
  <li>**API Gateway:** Spring Cloud Gateway (Roteamento de requisições, segurança, resiliência e rate limiting na borda)</li>
  <li>**Resiliência:** Resilience4j (Implementação de Circuit Breaker, Retries e Timeouts para chamadas síncronas e assíncronas)</li>
  <li>**Observabilidade:** Prometheus + Grafana (Coleta de métricas e dashboards para monitoramento do sistema)</li>
  <li>**Rastreamento Distribuído:** Spring Cloud Sleuth & Zipkin (Para correlacionar logs e rastrear requisições através dos microserviços)</li>
  <li>**Testes:** JUnit, Mockito, Testcontainers (Para testes unitários, de integração e end-to-end com infraestrutura real)</li>
</ul>

---

<h2>⚙️ Como Executar o Projeto Localmente</h2>

<p>Para colocar o OrderFlow em funcionamento em seu ambiente local, siga os passos abaixo:</p>

<ol>
  <li><strong>Clone o Repositório:</strong>
    <pre><code>git clone https://github.com/seu-usuario/orderflow.git</code></pre>
    <pre><code>cd orderflow</code></pre>
  </li>
  <li><strong>Configuração das Variáveis de Ambiente:</strong>
    <p>Para cada microserviço, navegue até seu diretório correspondente e configure as variáveis de ambiente com base nos arquivos <code>.env.example</code> fornecidos. Isso geralmente inclui configurações de banco de dados, RabbitMQ e portas.</p>
  </li>
  <li><strong>Levantar a Infraestrutura com Docker Compose:</strong>
    <p>Este comando irá construir as imagens Docker de todos os microserviços e subir a infraestrutura essencial (PostgreSQL, RabbitMQ, Eureka, Gateway, Prometheus, Grafana, Zipkin).</p>
    <pre><code>docker-compose up --build -d</code></pre>
    <p>Use <code>-d</code> para executar em modo detached (em segundo plano).</p>
  </li>
  <li><strong>Acessando o Sistema:</strong>
    <ul>
      <li>**Spring Cloud Gateway:** Geralmente em `http://localhost:8080` (verifique a porta configurada).</li>
      <li>**Eureka Dashboard:** `http://localhost:8761`</li>
      <li>**Grafana Dashboard:** `http://localhost:3000` (login padrão: admin/admin)</li>
      <li>**Zipkin UI:** `http://localhost:9411`</li>
    </ul>
    <p>Aguarde alguns instantes para que todos os serviços se registrem no Eureka e iniciem completamente.</p>
  </li>
</ol>

---

<h2>📬 Estratégias de Comunicação entre Serviços</h2>

<p>A comunicação entre os microserviços do OrderFlow é uma combinação estratégica para garantir a performance e a robustez:</p>

<ul>
  <li><strong>Comunicação Síncrona (REST via HTTP/1.1 com JSON):</strong>
    <ul>
      <li>Utilizada para operações que exigem uma resposta imediata e para fluxos onde a consistência instantânea é prioritária (ex: consultar detalhes de um produto no InventoryMS pelo OrderMS).</li>
      <li>Implementado com Spring Cloud OpenFeign para chamadas declarativas e integração com padrões de resiliência como Circuit Breaker e Retries.</li>
    </ul>
  </li>
  <li><strong>Comunicação Assíncrona (Mensageria via RabbitMQ com AMQP):</strong>
    <ul>
      <li>Empregada para eventos críticos e operações que não requerem uma resposta imediata, como o processamento de pagamentos ou atualizações de estoque que disparam notificações.</li>
      <li>Garante **desacoplamento** entre os serviços, **resiliência** (mensagens persistidas em filas) e **escalabilidade** (processamento paralelo de eventos).</li>
      <li>Utiliza Spring Cloud Stream com funções (<code>Function</code> e <code>Consumer</code>) para um modelo de programação reativo e expressivo.</li>
      <li>Contratos de eventos definidos em <code>/docs/contracts</code> para garantir a interoperabilidade.</li>
    </ul>
  </li>
</ul>

---

<h2>✅ Funcionalidades Principais</h2>

<p>O OrderFlow abrange as seguintes funcionalidades, demonstrando um fluxo completo de gerenciamento de pedidos em um ambiente de microserviços:</p>

<ul>
  <li>**Gerenciamento de Pedidos:** Criação, visualização e atualização do ciclo de vida de pedidos e carrinho de compras.</li>
  <li>**Controle de Estoque:** Atualização e consulta de disponibilidade de produtos em tempo real.</li>
  <li>**Processamento de Pagamentos:** Integração com gateway de pagamento, com implementação de retry e fallback para pagamentos com cartão de crédito, garantindo a resiliência da transação.</li>
  <li>**Geração de Relatórios:** Capacidade de gerar relatórios analíticos sobre vendas, estoque e outros dados relevantes.</li>
  <li>**Agendamento de Tarefas:** Execução de processos em horários pré-definidos (ex: envio de lembretes, consolidação de dados).</li>
  <li>**Notificações em Tempo Real:** Envio de comunicações (e-mail, SMS) aos usuários sobre o status do pedido, pagamento, etc.</li>
  <li>**Monitoramento e Métricas:** Coleta e visualização de métricas de performance e saúde de cada microserviço via Prometheus e Grafana.</li>
  <li>**Rastreamento Distribuído:** Capacidade de rastrear uma requisição completa através de múltiplos serviços usando Zipkin.</li>
</ul>

---

<h2>📈 Roadmap Futuro</h2>

<p>O desenvolvimento do OrderFlow é contínuo, com foco em aprimoramento e expansão das funcionalidades. Próximos passos planejados:</p>

<ul>
  <li>[x] Criar estrutura básica de microserviços</li>
  <li>[x] Integração com mensageria (RabbitMQ)</li>
  <li>[ ] Implementar Autenticação e Autorização robusta via OAuth2/OpenID Connect (AuthMS).</li>
  <li>[ ] Desenvolver uma Interface web intuitiva com Next.js para interação completa com o sistema.</li>
  <li>[ ] Expandir a cobertura de testes automatizados (unitários, integração e end-to-end com Testcontainers).</li>
  <li>[ ] Realizar o Deploy em ambiente de nuvem (AWS), explorando serviços como ECS/EKS, RDS, S3, etc.</li>
  <li>[ ] Implementar estratégias de Observabilidade Avançada (Distributed Tracing com OpenTelemetry).</li>
  <li>[ ] Adicionar Gateway de Pagamento real para simular transações.</li>
</ul>

---

<h2>🤝 Contribuição</h2>

<p>Contribuições são muito bem-vindas! Sinta-se à vontade para abrir uma issue para sugestões ou bugs, ou enviar um pull request com melhorias. Por favor, leia o <code>CONTRIBUTING.md</code> (se for criar um) para diretrizes.</p>

---

<h2>📄 Licença</h2>

<p>Este projeto está sob a licença <strong>MIT</strong>.</p>

---<h1 align="center">📦 OrderFlow</h1>
<p align="center">Sistema de gerenciamento de pedidos com arquitetura de microserviços escalável e resiliente</p>

<p align="center">
  <img src="https://img.shields.io/badge/status-Em%20desenvolvimento-yellow" alt="Status: Em Desenvolvimento" />
  <img src="https://img.shields.io/badge/arquitetura-Microservi%C3%A7os-blue" alt="Arquitetura: Microserviços" />
  <img src="https://img.shields.io/badge/resili%C3%AAncia-Resilience4J-orange" alt="Resiliência: Resilience4J" />
  <img src="https://img.shields.io/badge/observabilidade-Prometheus%20%26%20Grafana-red" alt="Observabilidade: Prometheus & Grafana" />
  <img src="https://img.shields.io/badge/licen%C3%A7a-MIT-green" alt="Licença: MIT" />
</p>

---

<h2>✨ Visão Geral</h2>

<p>
  <strong>OrderFlow</strong> é uma aplicação de gerenciamento de pedidos desenvolvida com foco em **escalabilidade**, **organização**, **desacoplamento** e **resiliência**. A arquitetura baseia-se em **microserviços** que se comunicam de forma síncrona (REST) e assíncrona (mensageria), garantindo um fluxo de trabalho robusto e eficiente desde a criação do pedido até a notificação do usuário. Este projeto visa demonstrar a aplicação de boas práticas e padrões arquiteturais em um ambiente distribuído.
</p>

---

<h2>📐 Arquitetura do Sistema</h2>

<p>
  O OrderFlow é composto pelos seguintes microserviços, cada um com sua responsabilidade bem definida e comunicando-se de forma eficiente para orquestrar o fluxo de pedidos:
</p>

<ul>
  <li><strong>OrderMS</strong>: Responsável pela lógica central de pedidos, incluindo o gerenciamento do carrinho de compras e o ciclo de vida dos pedidos. Inicia o processo de pagamento e interage com o Inventário.</li>
  <li><strong>InventoryMS</strong>: Gerencia o catálogo de produtos e o controle de estoque. Atualiza o estoque em tempo real e emite eventos para o OrderMS.</li>
  <li><strong>PaymentMS</strong>: Processa todas as transações de pagamento. Lida com integrações de gateway de pagamento e garante a resiliência em caso de falhas, com retry e fallback.</li>
  <li><strong>NotificationMS</strong>: (Sugiro renomear de UsuarioNotificationsMS para algo mais genérico como NotificationMS) Responsável pelo envio de notificações (e-mail, SMS, push) aos usuários em diversas etapas do pedido e pagamento.</li>
  <li><strong>AuthMS</strong>: Gerencia a autenticação de usuários e a autorização de acesso aos recursos dos microserviços via JWT/OAuth2.</li>
  <li><strong>ReportMS</strong>: Encarregado da geração de relatórios analíticos e operacionais com base nos dados consolidados do sistema.</li>
  <li><strong>ScheduleMS</strong>: Gerencia a execução de tarefas agendadas, como envio de lembretes ou gatilho para a geração de relatórios periódicos.</li>
</ul>

**Diagrama de Arquitetura:**
<p align="center">
  <img src="docs/arquitetura_orderflow.png" alt="Diagrama de Arquitetura OrderFlow" width="800"/>
  <br>
  <em>Um diagrama visual da arquitetura de microserviços e suas interações.</em>
</p>

---

<h2>🛠️ Tecnologias Chave & Ecossistema</h2>

<p>Este projeto foi construído utilizando um conjunto de tecnologias modernas para garantir robustez, escalabilidade e manutenibilidade:</p>

<ul>
  <li>**Linguagem e Framework:** Java 17+ & Spring Boot 3.x (com Spring Cloud para ecossistema de microserviços)</li>
  <li>**Banco de Dados:** PostgreSQL (Persistência de dados transacionais)</li>
  <li>**Mensageria:** RabbitMQ (para comunicação assíncrona baseada em eventos, garantindo desacoplamento e resiliência)</li>
  <li>**Containerização & Orquestração:** Docker & Docker Compose (para empacotamento e orquestração dos serviços em ambiente de desenvolvimento)</li>
  <li>**Descoberta de Serviços:** Spring Cloud Eureka (Registro e descoberta de instâncias de microserviços)</li>
  <li>**API Gateway:** Spring Cloud Gateway (Roteamento de requisições, segurança, resiliência e rate limiting na borda)</li>
  <li>**Resiliência:** Resilience4j (Implementação de Circuit Breaker, Retries e Timeouts para chamadas síncronas e assíncronas)</li>
  <li>**Observabilidade:** Prometheus + Grafana (Coleta de métricas e dashboards para monitoramento do sistema)</li>
  <li>**Testes:** JUnit, Mockito, Testcontainers (Para testes unitários, de integração e end-to-end com infraestrutura real)</li>
</ul>

---

<h2>⚙️ Como Executar o Projeto Localmente</h2>

<p>Para colocar o OrderFlow em funcionamento em seu ambiente local, siga os passos abaixo:</p>

<ol>
  <li><strong>Clone o Repositório:</strong>
    <pre><code>git clone https://github.com/seu-usuario/orderflow.git</code></pre>
    <pre><code>cd orderflow</code></pre>
  </li>
  <li><strong>Configuração das Variáveis de Ambiente:</strong>
    <p>Para cada microserviço, navegue até seu diretório correspondente e configure as variáveis de ambiente com base nos arquivos <code>.env.example</code> fornecidos. Isso geralmente inclui configurações de banco de dados, RabbitMQ e portas.</p>
  </li>
  <li><strong>Levantar a Infraestrutura com Docker Compose:</strong>
    <p>Este comando irá construir as imagens Docker de todos os microserviços e subir a infraestrutura essencial (PostgreSQL, RabbitMQ, Eureka, Gateway, Prometheus, Grafana, Zipkin).</p>
    <pre><code>docker-compose up --build -d</code></pre>
    <p>Use <code>-d</code> para executar em modo detached (em segundo plano).</p>
  </li>
  <li><strong>Acessando o Sistema:</strong>
    <ul>
      <li>**Spring Cloud Gateway:** Geralmente em `http://localhost:8080` (verifique a porta configurada).</li>
      <li>**Eureka Dashboard:** `http://localhost:8761`</li>
      <li>**Grafana Dashboard:** `http://localhost:3000` (login padrão: admin/admin)</li>
    </ul>
    <p>Aguarde alguns instantes para que todos os serviços se registrem no Eureka e iniciem completamente.</p>
  </li>
</ol>

---

<h2>📬 Estratégias de Comunicação entre Serviços</h2>

<p>A comunicação entre os microserviços do OrderFlow é uma combinação estratégica para garantir a performance e a robustez:</p>

<ul>
  <li><strong>Comunicação Síncrona (REST via HTTP/1.1 com JSON):</strong>
    <ul>
      <li>Utilizada para operações que exigem uma resposta imediata e para fluxos onde a consistência instantânea é prioritária (ex: consultar detalhes de um produto no InventoryMS pelo OrderMS).</li>
      <li>Implementado com Spring Cloud OpenFeign para chamadas declarativas e integração com padrões de resiliência como Circuit Breaker e Retries.</li>
    </ul>
  </li>
  <li><strong>Comunicação Assíncrona (Mensageria via RabbitMQ com AMQP):</strong>
    <ul>
      <li>Empregada para eventos críticos e operações que não requerem uma resposta imediata, como o processamento de pagamentos ou atualizações de estoque que disparam notificações.</li>
      <li>Garante **desacoplamento** entre os serviços, **resiliência** (mensagens persistidas em filas) e **escalabilidade** (processamento paralelo de eventos).</li>
      <li>Utiliza Spring Cloud Stream com funções (<code>Function</code> e <code>Consumer</code>) para um modelo de programação reativo e expressivo.</li>
      <li>Contratos de eventos definidos em <code>/docs/contracts</code> para garantir a interoperabilidade.</li>
    </ul>
  </li>
</ul>

---

<h2>✅ Funcionalidades Principais</h2>

<p>O OrderFlow abrange as seguintes funcionalidades, demonstrando um fluxo completo de gerenciamento de pedidos em um ambiente de microserviços:</p>

<ul>
  <li>**Gerenciamento de Pedidos:** Criação, visualização e atualização do ciclo de vida de pedidos e carrinho de compras.</li>
  <li>**Controle de Estoque:** Atualização e consulta de disponibilidade de produtos em tempo real.</li>
  <li>**Processamento de Pagamentos:** Integração com gateway de pagamento, com implementação de retry e fallback para pagamentos com cartão de crédito, garantindo a resiliência da transação.</li>
  <li>**Geração de Relatórios:** Capacidade de gerar relatórios analíticos sobre vendas, estoque e outros dados relevantes.</li>
  <li>**Agendamento de Tarefas:** Execução de processos em horários pré-definidos (ex: envio de lembretes, consolidação de dados).</li>
  <li>**Notificações em Tempo Real:** Envio de comunicações (e-mail, SMS) aos usuários sobre o status do pedido, pagamento, etc.</li>
  <li>**Monitoramento e Métricas:** Coleta e visualização de métricas de performance e saúde de cada microserviço via Prometheus e Grafana.</li>

</ul>

---

<h2>📈 Roadmap Futuro</h2>

<p>O desenvolvimento do OrderFlow é contínuo, com foco em aprimoramento e expansão das funcionalidades. Próximos passos planejados:</p>

<ul>
  <li>[x] Criar estrutura básica de microserviços</li>
  <li>[x] Integração com mensageria (RabbitMQ)</li>
  <li>[ ] Implementar Autenticação e Autorização robusta via OAuth2/OpenID Connect (AuthMS).</li>
  <li>[ ] Desenvolver uma Interface web intuitiva com Next.js para interação completa com o sistema.</li>
  <li>[ ] Expandir a cobertura de testes automatizados (unitários, integração e end-to-end com Testcontainers).</li>
  <li>[ ] Realizar o Deploy em ambiente de nuvem (AWS), explorando serviços como ECS/EKS, RDS, S3, etc.</li>
  <li>[ ] Implementar estratégias de Observabilidade Avançada (Distributed Tracing com OpenTelemetry).</li>
  <li>[ ] Adicionar Gateway de Pagamento real para simular transações.</li>
</ul>

---

<h2>🤝 Contribuição</h2>

<p>Contribuições são muito bem-vindas! Sinta-se à vontade para abrir uma issue para sugestões ou bugs, ou enviar um pull request com melhorias. Por favor, leia o <code>CONTRIBUTING.md</code> (se for criar um) para diretrizes.</p>

---

<h2>📄 Licença</h2>

<p>Este projeto está sob a licença <strong>MIT</strong>.</p>

---