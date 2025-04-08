<h1 align="center">📦 OrderFlow</h1>
<p align="center">Sistema de gerenciamento de pedidos com arquitetura de microserviços</p>

<p align="center">
  <img src="https://img.shields.io/badge/status-Em%20desenvolvimento-yellow" />
  <img src="https://img.shields.io/badge/arquitetura-Microservi%C3%A7os-blue" />
  <img src="https://img.shields.io/badge/licen%C3%A7a-MIT-green" />
</p>

---

<h2>🔍 Visão Geral</h2>

<p>
  <strong>OrderFlow</strong> é uma aplicação desenvolvida com foco em escalabilidade, organização e desacoplamento.
  Utiliza uma arquitetura baseada em <strong>microserviços</strong> com comunicação síncrona e assíncrona.
</p>

---

<h2>📐 Arquitetura</h2>

<ul>
  <li><strong>OrderMS</strong>: gerencia pedidos e carrinho de compras</li>
  <li><strong>InventoryMS</strong>: controla produtos e estoque</li>
  <li><strong>PaymentMS</strong>: processa pagamentos</li>
  <li><strong>UsuarioNotificationsMS</strong>: notifica usuários por e-mail ou outros canais</li>
  <li><strong>AuthMS</strong>: autenticação e autorização</li>
  <li><strong>ReportMS</strong>: geração de relatórios</li>
  <li><strong>ScheduleMS</strong>: consultas agendadas para gerar notificações / relatórios</li>
</ul>

---

<h2>🛠️ Tecnologias Utilizadas</h2>

<ul>
  <li>Java + Spring Boot</li>
  <li>PostgreSQL</li>
  <li>RabbitMQ / Kafka</li>
  <li>Docker & Docker Compose</li>
  <li>Prometheus + Grafana (observabilidade)</li>
</ul>

---

<h2>⚙️ Como Executar</h2>

<pre>
git clone https://github.com/seu-usuario/orderflow.git
cd orderflow
docker-compose up --build
</pre>

<p>Configure as variáveis de ambiente em cada microserviço com base nos arquivos <code>.env.example</code>.</p>

---

<h2>📬 Comunicação entre Serviços</h2>

<ul>
  <li>REST: entre serviços de leitura e escrita</li>
  <li>Mensageria (RabbitMQ/Kafka): eventos como <code>PedidoCriado</code>, <code>EstoqueBaixo</code>, <code>PagamentoConfirmado</code></li>
</ul>

---

<h2>✅ Funcionalidades</h2>

<ul>
  <li>Criação e gerenciamento de pedidos</li>
  <li>Controle de estoque</li>
  <li>Processamento de pagamentos</li>
  <li>Geração de relatórios</li>
  <li>Schedules</li>
  <li>Notificações em tempo real</li>
  <li>Monitoramento e métricas</li>
</ul>

---

<h2>📈 Roadmap</h2>

- [x] Criar estrutura básica de microserviços
- [x] Integração com mensageria
- [ ] Autenticação via OAuth
- [ ] Interface web com Next.js
- [ ] Implementação de testes automatizados
- [ ] Deploy na AWS

---

<h2>🤝 Contribuição</h2>

<p>Contribuições são muito bem-vindas! Sinta-se à vontade para abrir uma issue ou enviar um pull request.</p>

---

<h2>📄 Licença</h2>

<p>Este projeto está sob a licença <strong>MIT</strong>.</p>
