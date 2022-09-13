package by.lab6.compositions.cmp;

import by.lab6.compositions.AbstractComposition;
import lombok.SneakyThrows;

import java.util.Comparator;

public class CompositionComparator implements Comparator<AbstractComposition> {

    private Fields field;

    /**
     * @param field -- the field to compare to with
     */
    public CompositionComparator(Fields field) {
        this.field = field;
    }

    /**
     * @param o1 -- the composition to compare
     * @param o2 -- the composition to compare
     * @return true if by.lab6.compositions are equals, else false
     */
    @SneakyThrows
    @Override
    public int compare(AbstractComposition o1, AbstractComposition o2) {
        var method = AbstractComposition.class.getMethod("get" + field.name());
        if (method.getReturnType() == int.class) {
            return Integer.compare((int)method.invoke(o1), (int)method.invoke(o2));
        }
        return CharSequence.compare((String) method.invoke(o1), (String) method.invoke(o2));
    }
}
