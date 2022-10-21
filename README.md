# szdc-convert - Java Semester Project Specification

Java CLI tool that converts train timetables in xlsx format to more machine-friendly JSON.

## Introduction

The goal of the semester project is to develop a Java CLI tool that converts train timetables in xlsx format to more machine-friendly JSON. We would like to process timetables published by "Sprava zeleznicni dopravni cesty", known as SZDC. Originally, the timetables of SZDC are publicly available only in PDF format. We would like to make such data available also in JSON format for personal use. To allow better processing of input data, PDF is first converted to easily processable XSLX format.

![Timetable](/Screenshot%202022-10-21%20at%2011.45.53.png)

## Specification

Tools will support converting .xslx timetables in folder [xslx-timetables](/xslx-timetables/). Each train timetable measures time in offsets from first departure.
