package com.protobuf.example;

import com.google.protobuf.InvalidProtocolBufferException;
import com.protobuf.order.OrderProto;

public class OrderSerializeDeserializer {

    private static class Order {
        String name;
        String address;
        int orderId;

        @Override
        public String toString() {
            return "Order{" +
                    "name='" + name + '\'' +
                    ", address='" + address + '\'' +
                    ", orderId=" + orderId +
                    '}';
        }
    }

    public static byte[] serialize(Order order) {
        OrderProto.Order.Builder builder = OrderProto.Order.newBuilder();
        builder.setName(order.name);
        builder.setOrderAddress(order.address);
        builder.setOrderId(order.orderId);
        OrderProto.Order protoOrder = builder.build();
        return protoOrder.toByteArray();
    }

    public static Order deserialize(byte[] input) throws InvalidProtocolBufferException {
        Order order = new Order();
        OrderProto.Order protoOrder = OrderProto.Order.parseFrom(input);
        order.address = protoOrder.getOrderAddress();
        order.name = protoOrder.getName();
        order.orderId = protoOrder.getOrderId();
        return order;
    }

    public static void main(String args[]) throws Exception{

        Order expected = new Order();
        expected.name = "Tushar Roy";
        expected.address = "Seattle";
        expected.orderId = 124;
        byte[] input = serialize(expected);
        Order actual = deserialize(input);
        System.out.println(actual);
    }
}
