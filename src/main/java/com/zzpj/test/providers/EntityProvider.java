package com.zzpj.test.providers;

import com.zzpj.entities.Book;

import java.math.BigInteger;
import java.security.SecureRandom;

public class EntityProvider {
    static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    static SecureRandom rnd = new SecureRandom();


    public Book getBook(){
        Book book = new Book();
        book.setId(1l);
        book.setTitle(randomString(40));
        book.setAuthor(randomString(35));
        book.setPrice(BigInteger.valueOf(rnd.nextInt(45)));

        return book;
    }

    private String randomString( int len ){
        StringBuilder sb = new StringBuilder( len );
        for( int i = 0; i < len; i++ )
            sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
        return sb.toString();
    }
}
