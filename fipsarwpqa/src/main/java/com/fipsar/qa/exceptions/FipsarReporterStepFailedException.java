package com.fipsar.qa.exceptions;

public class FipsarReporterStepFailedException
extends RuntimeException {

	public FipsarReporterStepFailedException() {}

	public FipsarReporterStepFailedException(String paramString) {}

	public String toString()  {
		return "[Custom Reporter Step Failed Exception]";
	}
}

