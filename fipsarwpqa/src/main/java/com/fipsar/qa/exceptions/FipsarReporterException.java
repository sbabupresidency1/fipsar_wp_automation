package com.fipsar.qa.exceptions;

public class FipsarReporterException
extends Exception
{
	private String message;

	public FipsarReporterException() {}

	public FipsarReporterException(String message)  {
		this.message = message;
	}

	public FipsarReporterException(String message, Throwable ex)  {
		super(message, ex);
		this.message = message;
	}
	public String toString()  {
		return "[Custom Reporter Exception] " + this.message;
	}
}
