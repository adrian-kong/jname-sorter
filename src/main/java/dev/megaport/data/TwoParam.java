package dev.megaport.data;

import java.util.function.Function;

public final record TwoParam(String firstParam, String secondParam) {

    /**
     * Assumes two parameters in input
     *
     * @param delimiter splitting the parameters
     * @return TwoParameter object
     */
    public static Function<String, TwoParam> stringTwoParamFunction(String delimiter) {
        return input -> {
            try {
                String[] params = input.split(delimiter);
                return new TwoParam(params[0], params[1]);
            } catch (IndexOutOfBoundsException exception) {
                return null;
            }
        };
    }

    public static Function<TwoParam, String> twoParamStringFunction(String delimiter) {
        return param -> param.firstParam() + delimiter + param.secondParam();
    }

}
