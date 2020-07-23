package com.example.demo.model.common;

import java.util.function.Consumer;

public interface CustomDbUtils {

  public default <T> void setIfNotNull(final Consumer<T> setter, final T value) {
    if (value != null) setter.accept(value);
  }
}
