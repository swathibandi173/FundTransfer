package com.Banking.Fundtransfer.exception;

public class SearchResultsNotFoundException extends RuntimeException
{

	public SearchResultsNotFoundException()
	  {
		  super();
	  }
	  public SearchResultsNotFoundException(String message)
	  {
		  super(message);
	  }
	  
	  public SearchResultsNotFoundException(String message, Throwable t)
	  {
		  super(message,t);
	  }
	  
	  
}
