# BugTrackerPro – Issue Tracking & Analytics System

## 📖 Data Input
Data parsing is efficiently handled using Jackson's JSON tree model APIs (`JsonNode`, `ObjectNode`, `ArrayNode`, etc.). This approach ensures dynamic extraction and mapping of configuration parameters, user profiles, and commands directly from raw JSON structures.


## 📦 Project Architecture & Package Structure

### 🎫 Ticket Management

#### Package: `ticket`
Manages the core issue ecosystem, lifecycle, and specialized visualization formats.
* **`Ticket`**: The base entity class containing standard properties required for issue logging, augmented with an extra `description` field.
* **`UI`**: Implements the **Builder Pattern** for UI-type tickets. Encapsulates optional UI parameters within a single private constructor and fluid setter methods, eliminating parameter pollution.
* **`Feature`**: A concrete class representing feature requests. Uses a standard constructor since all its configuration fields are strictly mandatory.
* **`Bug`**: Implements the **Builder Pattern** to handle varied optional diagnostic fields (e.g., environment setups, reproduction steps).
* **`ViewTickets`**: An inventory utility wrapper managing lists of issues alongside security filters for role-based printing (tailoring layouts for *Developer* vs. *Manager* permissions).

#### Package: `modifyTickets`
Handles post-creation mutations and automated lifecycle shifts.
* **`SpecialMention` `[Interface]`**: The core abstraction layer driving automated priority shifts.
* **`NextPriority` / `TransformCritical`**: Implementations triggered exactly 3 days post-milestone creation to escalate ticket weights using the **Strategy Pattern**.


### 👤 User Directory

#### Package: `user`
* **`Users`**: The structural baseline object representing a generic platform member.
* **`Developer`, `Manager`, `Reporter`**: Specialized domain actors extending the core `Users` blueprint.

#### Package: `developerTypes`
* **`DeveloperFactory`**: A creational **Factory Pattern** implementation abstracting developer initialization.
* **`JuniorDeveloper`, `MidDeveloper`, `SeniorDeveloper`**: Distinct runtime roles enforcing seniority levels and individual baseline processing power.


### 🕹️ Operations

#### Package: `commands`
Each executable simulation flow is strictly isolated into dedicated execution blocks:
* **`Report Ticket`**: Creates issues via standard instantiation (`Feature`) or fluent components (`UI`/`Bug` Builders). Injects dynamic timestamps, core data tags, and optional attributes.
* **`View ticket`**: Formats and exports the active ticket inventory safely into structured raw JSON via `ObjectNode`.
* **`Create milestone`**: Generates new project checkpoints with thorough exception handling for semantic boundary checks. Manages delayed ticket impacts via the `SpecialMention` escalation interface (**Strategy Pattern**).
* **`View milestones`**: Iterates over and prints the current status of all project milestones.
* **`Assign ticket`**: Dispatches specific tasks to developers. It evaluates strict safety locks, mapping constraints like matching `expertiseArea`, required `seniority`, and milestone blocks. Work assignments leverage a **Factory Method** tuned specifically for `Junior`, `Mid`, and `Senior` performance curves.
* **`View assigned tickets`**: Aggregates and dumps issues tied to a single target developer profile.
* **`Undo assigned tickets`**: Rolls back an allocation, pushing the task into `gaveupTickets` to preserve assignment history logs.
* **`Add comment`** & **`Undo add comment`**: Appends or deletes discussion points attached to an issue.
* **`Change status`** & **`Undo change status`**: Transitions tickets sequentially. When marked as `resolved`, it permanently writes lifecycle endpoints (`solvedAt`) and cache timestamps (`ultimTimestampCR`).
* **`Print ticket history`**: Traces historic changes, optimizing the visual breakdown according to the querying user's authorization level (*Developer* vs. *Manager*).
* **`Search`**: A multi-criteria filtering engine traversing the complete user registry and ticket cache.
* **`View notifications`**: An asynchronous message distribution layer leveraging the **Observer Pattern** (where project milestones act as observables/subjects, and developer profiles act as registered observers).
* **`Generate customer impact report`**: Aggregates total `open` / `in progress` tasks segmented clearly by issue type and priority.
* **`Generate ticket risk report`**: Performs analytical risk scans similar to the customer impact workflow.
* **`Generate resolution efficiency report`**: Tracks individual performance metrics to measure aggregate fix speeds.
* **`Generate performance report`**: Runs comparative calculations evaluating overall development quality.


### 🌐 Project Infrastructure & Search

#### Package: `milestones`
* **`Milestone`**: Core structural entity modeling project release targets and timelines.
* **`InfoMilestone`**: A helper class decoupling display layers from baseline milestone entities.

#### Package: `searching`
* **`DevelopersSearch`**: Provides structural filtering loops to isolate target developers based on queries.
* **`TicketSearch`**: Evaluates active system collections to locate specific tasks matching user filters.

#### Root Level Extensions
* **`Notifications`**: Standard object structure modeling cross-platform event updates.
* **`PerformanceReport`**: Base data representation powering metrics analytics and velocity charts.


### ⚡ Utilities & Constants

#### Package: `helpers`
Decoupled logic containers designed to keep core simulation components clean and maintainable:
* **`CheckingHelpers`** – Evaluates boundary conditions and rule constraint safety.
* **`HelperMethods`** – Core shared business logic utilities.
* **`PrintingHelpers`** – Standardizes complex text and console output formatting.
* **`ReturnHelpers`** – Handles safe data filtering and sub-entity extraction.
* **`WorkingWithMilestones`** – Recalculates, shifts, and triggers updates on milestones relative to execution timestamps.

#### Package: `magicNumbers`
Centralized application configurations and mathematical boundaries:
* **`MagicNumbersDouble`** – Stores constant `Double` values used throughout analytics calculations.
* **`MagicNumbersInt`** – Holds system structural indices, boundaries, and integer defaults.


### 💡 Core Notes & Implementation Safeguards
* **Assignment Failures (Tests 18 & 19)**: Implements specialized handling for developers unable to clear an assigned task due to operational conflicts (such as unexpected `expertiseArea` mismatches or lack of `seniority`).
* **Resource Balancing**: Issues are automatically unassigned and the estimated completion timelines are dynamically adjusted across remaining tickets to prevent layout drift.
