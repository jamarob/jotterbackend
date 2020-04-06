# jotterbackend

[![Java Version](https://img.shields.io/badge/Java-13-informational)](https://shields.io/) &nbsp; 
[![Spring Version](https://img.shields.io/badge/Spring%20Boot-2.2.6-informational)](https://shields.io/)

This is the REST backend for [Jotter](https://github.com/jamarob/jotter), my final project from the [neuefische bootcamp](https://www.neuefische.de/).

## Available routes

| Method | URL      | Purpose            |
|--------|----------|--------------------|
| GET    | /notes   | Retrieve all notes |
| PUT    | /notes   | Set all notes      |
| POST   | /notes   | Create a new note  |
| GET    | /note/ID | Get note by ID     |
| PUT    | /note/ID | Update note by ID  |
| DELETE | /note/ID | Delete note by ID  |

## JSON
Responses with notes have the following shape
```
{
    "id": string,
    "text": string,
    "created": string,
    "edited": string,
}
```
