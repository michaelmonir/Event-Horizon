package com.EventHorizon.EventHorizon.UtilityClasses;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class DateFunctionsTest {

  @Test
  void testIsDateAfterNow() {
    // Arrange
    java.sql.Date date = mock(java.sql.Date.class);
    when(date.after(Mockito.<java.util.Date>any())).thenReturn(true);

    // Act
    boolean actualIsDateAfterNowResult = DateFunctions.isDateAfterNow(date);

    // Assert
    verify(date).after(Mockito.<java.util.Date>any());
    assertTrue(actualIsDateAfterNowResult);
  }

  @Test
  void testIsDateBeforeNow() {
    // Arrange
    java.sql.Date date = mock(java.sql.Date.class);
    when(date.before(Mockito.<java.util.Date>any())).thenReturn(true);

    // Act
    boolean actualIsDateBeforeNowResult = DateFunctions.isDateBeforeNow(date);

    // Assert
    verify(date).before(Mockito.<java.util.Date>any());
    assertTrue(actualIsDateBeforeNowResult);
  }
}
