# szdc-convert - Java Semester Project Specification

Java CLI tool that converts train timetables in xlsx format to more machine-friendly JSON.

## Introduction

The goal of the semester project is to develop a Java CLI tool that converts train timetables in xlsx format to more machine-friendly JSON. We would like to process timetables published by "Sprava zeleznicni dopravni cesty", known as SZDC. Originally, the timetables of SZDC are publicly available only in PDF format. We would like to make such data available also in JSON format for personal use. To allow better processing of input data, PDF is first converted to easily processable XSLX format.

In PDF documents, schedule of each train is represented in the table below. Columns 3 contains duration of each travel from station to station, column 5 contains arrival times and column 7 departures.

![PDF](/Screenshot%202022-10-21%20at%2011.45.53.png)

PDF is converted to .xsls format, with structure that can be easily processed by the tool. Converted .xlsx files available at [xlsx](/xlsx/).

![Excel](/Screenshot%202022-10-21%20at%2011.49.29.png)

For this example of train EX 102, the tool will produce following output:

```json
"102": {
      "schedule": [
        {
          "departureTime": {
            "hours": 17,
            "minutes": 22,
            "isAfter30Seconds": false
          },
          "arrivalTime": {
            "hours": 17,
            "minutes": 9,
            "isAfter30seconds": false
          },
          "stationName": "Bohumín os.n."
        },
        {
          "departureTime": {
            "hours": 17,
            "minutes": 29,
            "isAfter30Seconds": false
          },
          "arrivalTime": null,
          "stationName": "Dětmarovice"
        },
        {
          "departureTime": {
            "hours": 17,
            "minutes": 30,
            "isAfter30Seconds": true
          },
          "arrivalTime": null,
          "stationName": "Odb Závada"
        },
        {
          "departureTime": {
            "hours": 17,
            "minutes": 34,
            "isAfter30Seconds": false
          },
          "arrivalTime": null,
          "stationName": "Petrovice u Karviné"
        }
      ],
      "trainType": "Ex"
    }

```

## Specification

The tool will convert .xlsx documents to JSON representation as collection of train schedules, with the following rules:

- For each train, the following data will be supported:
  - Arrival times (not in offset format as in PDF, exact time will be computed)
  - Departure times (not in offset format as in PDF, exact time will be computed)
  - Information whether times should be offseted by another 30 seconds (symbol '5' in table) in time cells
  - Name of station
- If train schedule can't be processed, user is provided with error log
  - Entire execution is not haulted in case of recoverable failure of single train schedule
- Train type information is provided (Ex, R, Os, ...)

## Analysis

Libraries for processing xlsx format and generating JSON files will be used. The most difficult part is to correctly process xlsx format -- files contain so called _merged cells_ which are cell spanning across multiple rows. Processing other information after rows / columns after merged must be correctly offseted. Cell context must be properly parsed (e.g. station names). Logic to compute exact times from time offsets in tables must be implemented.

Tool can be structured into several classes to be modular enough to support other output formats (e.g. XML) and processing can be structured as sequence of several iterators. Exception handling needs to be implemented correctly.

## Additional Notes

The project will use Maven, with `org.apache.poi` libraries for reading the files in a streamed manner (not keeping entire file in memory). Test xlsx files will be provided. This tool was developed as part of my bachelor’s thesis and needs to be refactored / properly documented.
