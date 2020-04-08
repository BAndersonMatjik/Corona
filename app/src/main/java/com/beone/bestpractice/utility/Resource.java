package com.beone.bestpractice.utility;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

//create mitchtabian  https://github.com/mitchtabian/Local-db-Cache-Retrofit-REST-API-MVVM/blob/master/app/src/main/java/com/codingwithmitch/foodrecipes/util/Resource.java
public class Resource<T>{

  @NonNull
  public final Status status;

  @Nullable
  public final T data;

  @Nullable
  public final String message;

  public Resource(@NonNull Status status, @Nullable T data, @Nullable String message) {
    this.status = status;
    this.data = data;
    this.message = message;
  }

  public static <T> Resource<T> success(@NonNull T data) {
    return new Resource<>(Status.SUCCESS, data, null);
  }

  public static <T> Resource<T> error(@NonNull String msg, @Nullable T data) {
    return new Resource<>(Status.ERROR, data, msg);
  }

  public static <T> Resource<T> loading(@Nullable T data) {
    return new Resource<>(Status.LOADING, data, null);
  }

  public enum Status { SUCCESS, ERROR, LOADING}
}
