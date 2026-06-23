PROJECT CONTEXT

I am building an enterprise-grade backend project called:

AIOps – AI Powered Observability & Debugging Platform
Objective

The project aims to help developers debug applications in real-time using AI.

The platform will:

Collect logs from applications
Analyze logs using AI
Perform root cause analysis
Suggest fixes
Create incidents automatically
Send alerts
Provide observability dashboards
Support real-time monitoring

The goal is to showcase:

Spring Boot
Microservices
Kafka
Spring AI
PostgreSQL
MongoDB
Docker
DevOps
System Design
Architecture Design
Backend Development

This project should look like something built in a real enterprise environment.

SDLC STATUS

Completed:

✅ Step 1: SRS Document

✅ Step 2: System Architecture Design

✅ Step 3: Database Design

✅ Step 4: Project Structure & Microservices Setup

✅ Step 5: Development Roadmap

✅ Step 6: API Design & Service Contracts

✅ Step 7: Technical Design Document (TDD)

✅ Step 8: Sprint Planning

✅ Step 9: Domain Modeling & Bounded Context Design

✅ Step 10: Low-Level Design (LLD)

Current Status:

🚀 Ready to start coding.

Next Step:

Step 11: Auth Service Implementation

SYSTEM ARCHITECTURE
React Frontend
       |
API Gateway
       |
------------------------------------------------
| Auth | Log | AI | Notification | Monitoring |
------------------------------------------------
       |
      Kafka
       |
------------------------
| PostgreSQL | MongoDB |
------------------------

Architecture Style:

Microservices Architecture
Event-Driven Architecture
Domain Driven Design (DDD)
Cloud-Native Design
MICROSERVICES
Auth Service

Responsibilities:

Registration
Login
JWT
RBAC

Database:

PostgreSQL
Log Service

Responsibilities:

Receive logs
Store logs
Publish Kafka events

Database:

MongoDB
AI Analysis Service

Responsibilities:

Spring AI integration
Root cause analysis
Error summarization
Fix recommendations

Database:

PostgreSQL

Consumes:

LOG_RECEIVED

Publishes:

ANALYSIS_COMPLETED
Notification Service

Responsibilities:

Email notifications
Alert delivery

Consumes:

INCIDENT_CREATED
ALERT_GENERATED
Monitoring Service

Responsibilities:

Metrics
Health checks
Observability

Publishes:

ALERT_GENERATED
API Gateway

Responsibilities:

Routing
Authentication
JWT validation
DATABASE DESIGN
PostgreSQL Tables
users
id
name
email
password
role
created_at
services
id
service_name
description
owner_id
created_at
incidents
id
service_id
title
description
severity
status
assigned_to
created_at
alerts
id
service_id
message
alert_type
triggered_at
ai_analysis_results
id
log_id
summary
root_cause
suggested_fix
confidence_score
analyzed_at
MongoDB Collection
logs
{
  "id": "",
  "serviceName": "",
  "level": "",
  "message": "",
  "traceId": "",
  "timestamp": ""
}
DOMAIN MODELING
Authentication Context

Aggregate Root:

User

Entities:

User
Role
RefreshToken

Owned By:

auth-service

Logging Context

Aggregate Root:

Log

Entities:

Log
LogMetadata
TraceInfo

Owned By:

log-service

AI Analysis Context

Aggregate Root:

AnalysisResult

Entities:

AnalysisRequest
AnalysisResult
SuggestedFix
RootCause

Owned By:

ai-analysis-service

Incident Context

Aggregate Root:

Incident

Entities:

Incident
IncidentComment
IncidentAssignment

Owned By:

incident-service (future)

Initially managed by log-service.

Notification Context

Aggregate Root:

Notification

Owned By:

notification-service

Monitoring Context

Aggregate Root:

ServiceHealth

Owned By:

monitoring-service

EVENT OWNERSHIP

LOG_RECEIVED

Producer:

log-service

Consumer:

ai-analysis-service

ANALYSIS_COMPLETED

Producer:

ai-analysis-service

Consumer:

incident-service

INCIDENT_CREATED

Producer:

incident-service

Consumer:

notification-service

ALERT_GENERATED

Producer:

monitoring-service

Consumer:

notification-service

TECHNOLOGY STACK

Language:

Java 21

Backend:

Spring Boot

Security:

Spring Security
JWT
BCrypt

API Gateway:

Spring Cloud Gateway

Messaging:

Kafka

AI:

Spring AI

Databases:

PostgreSQL
MongoDB

Documentation:

Swagger / OpenAPI

Logging:

SLF4J
Logback

Monitoring:

Spring Actuator
Prometheus
Grafana

Tracing:

OpenTelemetry (later)

Containers:

Docker
Docker Compose

CI/CD:

GitHub Actions

Frontend:

React

Deployment:

Render → AWS (future)

REPOSITORY STRUCTURE
aiops-platform/

├── api-gateway
├── auth-service
├── log-service
├── ai-analysis-service
├── notification-service
├── monitoring-service
├── common-lib
├── docker
├── docs
├── scripts
├── docker-compose.yml
└── README.md
COMMON LIBRARY

Package:

com.aiops.common

Contains:

ApiResponse
ErrorResponse
Constants
Kafka Events
Utilities
Exceptions
API STANDARDS

Base URL:

/api/v1

Standard Success Response:

{
  "success": true,
  "message": "",
  "data": {}
}

Standard Error Response:

{
  "success": false,
  "message": "",
  "errorCode": "",
  "timestamp": ""
}
AUTH SERVICE LLD

Package Structure:

controller
service
service.impl
repository
entity
dto
mapper
security
exception
config

Entity:

User

Fields:

id
name
email
password
role
active

Role Enum:

ADMIN
DEVELOPER
VIEWER

DTOs:

RegisterRequest
LoginRequest
LoginResponse

Repository:

UserRepository

Services:

AuthService
JwtService

Security:

SecurityConfig
JwtAuthenticationFilter

Endpoints:

POST /api/v1/auth/register
POST /api/v1/auth/login
GET /api/v1/auth/me
DEVELOPMENT ROADMAP

Build in this order:

Auth Service
API Gateway
Log Service
MongoDB Integration
Kafka Integration
AI Analysis Service
Incident Management
Notification Service
React Dashboard
Monitoring
Dockerization
CI/CD
Deployment
CURRENT TASK

We have completed all planning and design phases.

Do not redesign architecture.

Do not revisit previous decisions unless absolutely necessary.

Start implementation from:

🚀 Auth Service

Implementation order:

Spring Boot Project Setup
PostgreSQL Configuration
User Entity
User Repository
JWT Security
Register API
Login API
Swagger
Dockerfile

Act as a Senior Software Architect + Senior Spring Boot Developer and guide me step-by-step through implementation with production-grade practices.
