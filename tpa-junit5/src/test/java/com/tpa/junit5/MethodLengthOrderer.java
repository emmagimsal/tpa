package com.tpa.junit5;

import java.util.Comparator;

import org.junit.jupiter.api.MethodDescriptor;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.MethodOrdererContext;

class MethodLengthOrderer implements MethodOrderer {

    private Comparator<MethodDescriptor> comparator =
            Comparator.comparingInt(methodDescriptor -> methodDescriptor.getMethod().getName().length());

    @Override
    public void orderMethods(MethodOrdererContext context) {
        context.getMethodDescriptors().sort(comparator);
    }
}