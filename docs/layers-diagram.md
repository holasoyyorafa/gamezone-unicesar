# Layers Diagram

> Show the four layers (model, persistence, service, ui), which classes
> belong to each, and the allowed dependency direction:
> ui -> service -> persistence -> model (model depends on nothing).

```mermaid
flowchart TD
    subgraph UI[ui layer]
    end
    subgraph Service[service layer]
    end
    subgraph Persistence[persistence layer]
    end
    subgraph Model[model layer]
    end

    UI --> Service
    Service --> Persistence
    Service --> Model
    Persistence --> Model
```
