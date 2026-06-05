# 🎬 CineDay

Uma plataforma inteligente de recomendação de filmes desenvolvida com Java, utilizando conceitos avançados de Programação Orientada a Objetos, princípios SOLID e testes automatizados.

---

## 📋 Descrição do Projeto

**CineDay** é um sistema de recomendação de filmes que utiliza algoritmos sofisticados para sugerir títulos alinhados às preferências individuais dos usuários. 

O sistema analisa múltiplos critérios como:
- 🎭 Gênero cinematográfico
- ⏱️ Duração do filme
- 🌍 Idioma
- 🔞 Classificação etária
- ⭐ Avaliações e scores

A arquitetura foi desenvolvida seguindo as melhores práticas de engenharia de software, garantindo código modular, testável e fácil de manter.

---

## 🛠️ Tecnologias & Stack

| Tecnologia | Versão | Propósito |
|-----------|--------|----------|
| **Java** | 22 | Linguagem principal |
| **JUnit** | 5 | Framework de testes unitários |
| **Mockito** | - | Criação de mocks para testes |
| **UML** | 2.0 | Modelagem e diagramação |

---

## 📁 Arquitetura do Projeto

```
src/
├── model/              # Classes de domínio (Film, User, Preference)
├── service/            # Lógica de negócio (RecommendationEngine, FilterService)
├── util/               # Utilitários e helpers
├── exceptions/         # Exceções customizadas
├── catalogo/           # Gerenciamento do catálogo de filmes
└── test/               # Testes unitários e de integração
```

---

## ✨ Funcionalidades Principais

- ✅ **Recomendação Inteligente**: Algoritmo de matching baseado em preferências
- 🎯 **Cálculo de Score**: Sistema de pontuação para compatibilidade filme-usuário
- 🔍 **Filtragem Avançada**: Busca com múltiplos critérios simultâneos
- 🎲 **Recomendação Aleatória**: Sugestões surpresa do catálogo
- 🧪 **Cobertura de Testes**: Suite completa com JUnit 5 e Mockito
- 📊 **Catálogo Expandível**: Estrutura preparada para crescimento

---

## 🏗️ Princípios SOLID Implementados

O projeto demonstra a aplicação rigorosa dos cinco princípios SOLID:

### **S** - Single Responsibility Principle
Cada classe tem uma única responsabilidade bem definida (ex: `RecommendationService` apenas recomenda, `FilterService` apenas filtra)

### **O** - Open/Closed Principle
Classes abertas para extensão, fechadas para modificação através de interfaces genéricas

### **L** - Liskov Substitution Principle
Implementações de interfaces podem ser substituídas sem quebrar a funcionalidade

### **I** - Interface Segregation Principle
Interfaces segregadas e específicas para cada comportamento

### **D** - Dependency Inversion Principle
Dependências injetadas através de construtores e interfaces

---

## 🧪 Testes Automatizados

O projeto utiliza uma estratégia robusta de testes:

- **Testes Unitários**: Validação de métodos isolados
- **Mocks com Mockito**: Simulação de dependências externas
- **Cobertura**: Alta cobertura de linhas críticas
- **Execução**: Integrados ao ciclo de desenvolvimento

```bash
# Executar testes
mvn test

# Gerar relatório de cobertura
mvn jacoco:report
```

---

## 📐 Diagramas & Modelagem

O projeto inclui diagramas UML detalhados:

- **Diagrama de Classes**: Visualização da hierarquia e relacionamentos
- **Diagrama de Sequência**: Fluxo de execução das recomendações

---

## 🚀 Como Usar

### Pré-requisitos
- Java 22+
- Maven 3.8+

### Compilação
```bash
mvn clean compile
```

### Execução de Testes
```bash
mvn test
```

### Build do Projeto
```bash
mvn clean package
```

---

## 💡 Conceitos Aplicados

✓ **Programação Orientada a Objetos (POO)**  
✓ **Design Patterns** (Strategy, Factory, Dependency Injection)  
✓ **Encapsulamento** e abstração  
✓ **Polimorfismo** e herança  
✓ **Tratamento de Exceções**  
✓ **Algoritmos de Recomendação**  

---

## 👥 Contribuidores

<table>
  <tr>
    <td align="center">
      <a href="https://github.com/CristiankAmorim">
        <strong>Cristian Amorim</strong>
      </a>
    </td>
    <td align="center">
      <strong>Douglas Rufino</strong>
    </td>
  </tr>
</table>

---

## 📝 Licença

Este projeto é de código aberto e foi desenvolvido para fins educacionais.

---

## 🎯 Conclusão

CineDay demonstra a aplicação prática de conceitos avançados de engenharia de software em um sistema real, integrando:
- Arquitetura limpa e escalável
- Código testável e mantível
- Boas práticas de desenvolvimento
- Padrões de design consolidados

Um excelente exemplo de como aplicar teoria em prática! 🎉
