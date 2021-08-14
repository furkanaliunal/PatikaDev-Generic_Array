package GenericArray;

public class GenericArray<T> {
    private Value<T>[] array;
    private int lastIndex = 0;

    public GenericArray(){
        array = new Value[10];
    }
    public GenericArray(int capacity) {
        array = new Value[capacity];
    }

    public void add(T value){
        if (lastIndex == this.array.length){
            doubleIndexLimit();
        }
        array[lastIndex] = new Value(value);
        lastIndex++;
    }

    public T get(int index){
        if (array[index] == null){
            return null;
        }
        return this.array[index].getValue();
    }

    public T remove(int index){
        if (index > lastIndex){
            return null;
        }
        Value<T>[] save = this.array;
        int size = array.length;
        Value<T> returnValue = array[index];
        array = new Value[size];
        for (int i = 0; i < lastIndex; i++){
            if (save[i] == null){
                break;
            }
            if (i == index){
                continue;
            }
            array[i] = save[i];
        }
        lastIndex--;
        return returnValue.getValue();
    }

    public T set(int index, T value){
        if (index > lastIndex){
            return null;
        }
        this.array[index].setValue(value);
        return this.array[index].getValue();
    }

    public int size(){
        return this.lastIndex;
    }

    public int getCapacity(){
        return this.array.length;
    }

    public int indexOf(T value){
        for (int i = 0 ; i < lastIndex; i++){
            if (array[i].getValue() == value){
                return i;
            }
        }
        return -1;
    }
    public int lastIndexOf(T value){
        for (int i = lastIndex - 1; i >= 0; i--){
            if (array[i].getValue() == value){
                return i;
            }
        }
        return -1;
    }
    public Boolean isEmpty(){
        return lastIndex == 0;
    }

    /*public T[] toArray(){
        ArrayList<T> array = new ArrayList();
        for (Value<T> value : this.array){
            if (value == null || value.getValue() == null){
                continue;
            }
            array.add(value.getValue());
        }
        return (T[]) array.toArray();
    }*/

    public Object[] toArray(){
        Object[] array = new Object[size()];

        for(int i = 0; i < array.length; i++){
            if (this.array[i] == null || this.array[i].getValue() == null){
                continue;
            }
            array[i] = this.array[i].getValue();
        }
        return array;
    }

    public void clear(){
        lastIndex = 0;
        array = new Value[10];
    }

    public Object[] subList(int start, int finish){
        Object[] array = new Object[finish - start + 1];
        int arrayIndex = 0;
        for(int i = start; i < finish; i++){
            array[arrayIndex++] = this.array[i].getValue();
        }
        return array;
    }

    public Boolean contains(T value){
        for (Value<T> loopValue : array){
            if (loopValue == null || loopValue.getValue() == null){
                continue;
            }
            if (loopValue.getValue() == value){
                return true;
            }
        }
        return false;
    }

    public String toString(){
        String result = "[";
        for (int i = 0; i < size(); i++){
            if (array[i] == null || array[i].getValue() == null){
                continue;
            }
            result += array[i].getValue();
            if (i != size() - 1){
                result += ", ";
            }
        }
        /*for (Value<T> v : array){
            if (v == null){
                continue;
            }
            result += v.getValue() + ", ";
        }
        return result.substring(0, result.length() - 2) + "]";*/
        return result + " ]";
    }

    private void doubleIndexLimit(){
        Value<T>[] save = this.array;
        int size = this.array.length;
        this.array = new Value[size*2];
        for (int i = 0; i < save.length; i++){
            array[i] = save[i];
        }
    }

}
