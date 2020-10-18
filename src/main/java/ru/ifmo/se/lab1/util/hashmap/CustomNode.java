package ru.ifmo.se.lab1.util.hashmap;

import java.util.Objects;

public class CustomNode<K, V> {
    CustomEntry<K, V> value;
    CustomNode<K, V> next;

    public CustomNode(CustomEntry<K, V> value, CustomNode<K, V> next) {
        this.value = value;
        this.next = next;
    }

    public CustomEntry<K, V> getValue() {
        return value;
    }

    public CustomNode<K, V> getNext() {
        return next;
    }

    public void setNext(CustomNode<K, V> next) {
        this.next = next;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomNode<?, ?> that = (CustomNode<?, ?>) o;
        return value.equals(that.value) &&
                Objects.equals(next, that.next);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, next);
    }

    @Override
    public String toString() {
        return value.toString();
    }
}