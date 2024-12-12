# Ticketing Application

## Introduction
The Ticketing Application is a multi-threaded system designed to manage event ticketing operations. It features a producer-consumer model where vendors add tickets to a pool, and customers retrieve them. The system supports configuration through both a command-line interface (CLI) and a web-based UI built with Angular. The application also includes real-time updates, concurrency handling, and user-friendly controls for managing the ticketing simulation.

---

## Setup Instructions

### Prerequisites
To build and run the application, ensure the following software is installed:

- **Java**: Version 17 or higher
- **Maven**: For building the Spring Boot backend
- **Node.js**: Version 16 or higher (includes npm)
- **Angular CLI**: Version 15 or higher (`npm install -g @angular/cli`)

### Backend Setup
1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd TicketingApp
   ```

2. Build the Spring Boot application:
   ```bash
   mvn clean install
   ```

3. Run the backend:
   ```bash
   mvn spring-boot:run
   ```

The backend server will start at `http://localhost:8080`.

### Frontend Setup
1. Navigate to the frontend directory:
   ```bash
   cd frontend
   ```

2. Install dependencies:
   ```bash
   npm install
   ```

3. Run the Angular application:
   ```bash
   ng serve
   ```

The frontend will be accessible at `http://localhost:4200`.

---

## Usage Instructions

### Configuring the System
- **CLI Configuration**:
  1. When the application starts, it will prompt you to load a saved configuration or create a new one.
  2. Enter the following parameters when prompted:
     - Maximum Ticket Capacity
     - Total Tickets
     - Ticket Release Rate (tickets/second)
     - Customer Retrieval Rate (tickets/second)

- **UI Configuration**:
  1. Open the web interface at `http://localhost:4200`.
  2. Navigate to the **Configuration Form**.
  3. Enter the desired parameters and click **Submit**.

### Starting and Stopping the System
- **Start the Simulation**:
  - In the **Control Panel**, click the **Start** button to initialize vendor and customer threads.
  
- **Stop the Simulation**:
  - Click the **Stop** button in the **Control Panel** to halt all threads and stop ticket operations.

### Explanation of UI Controls
- **Control Panel**:
  - Displays system status, total tickets, tickets sold, and tickets bought.
  - Contains **Start** and **Stop** buttons to manage the simulation.

- **Configuration Form**:
  - Allows setting up ticketing parameters such as capacity and rates.
  - Validates inputs to ensure values are positive integers.

---

## Notes
- Ensure the backend is running before starting the frontend.
- Periodic polling is used in the frontend to fetch real-time ticket stats from the backend.
- All configuration changes are persisted for future use.

---

For additional details, please refer to the project documentation or contact the development team.

