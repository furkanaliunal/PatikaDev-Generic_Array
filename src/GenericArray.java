public class GenericArray<T>{
    private Object[] array;
    private int lastIndex = 0;

    public GenericArray(){
        array = new Object[10];
    }
    public GenericArray(int capacity) {
        array = new Object[capacity];
    }

    public void add(T value){
        if (lastIndex == this.array.length){
            doubleIndexLimit();
        }
        array[lastIndex] = value;
        lastIndex++;
    }

    public T get(int index){
        if (array[index] == null){
            return null;
        }
        return (T) this.array[index];
    }

    public T remove(int index){
        if (index > lastIndex){
            return null;
        }
        Object[] save = this.array;
        int size = array.length;
        T returnValue = (T) array[index];
        array = new Object[size];
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
        return returnValue;
    }

    public T set(int index, T value){
        if (index > lastIndex){
            return null;
        }
        this.array[index] = value;
        return (T) this.array[index];
    }

    public int size(){
        return this.lastIndex;
    }

    public int getCapacity(){
        return this.array.length;
    }

    public int indexOf(T value){
        for (int i = 0 ; i < lastIndex; i++){
            if (array[i] == value){
                return i;
            }
        }
        return -1;
    }
    public int lastIndexOf(T value){
        for (int i = lastIndex - 1; i >= 0; i--){
            if (array[i] == value){
                return i;
            }
        }
        return -1;
    }
    public Boolean isEmpty(){
        return lastIndex == 0;
    }

    public Object[] toArray(){
        Object[] array = new Object[size()];
        for(int i = 0; i < array.length; i++){
            if (this.array[i] == null){
                continue;
            }
            array[i] = this.array[i];
        }
        return array;
    }

    public void clear(){
        lastIndex = 0;
        array = new Object[10];
    }

    public GenericArray subList(int start, int finish){
        GenericArray subList = new GenericArray();
        for(int i = start; i <= finish; i++){
            subList.add(this.array[i]);
        }
        return subList;
    }

    public Boolean contains(T value){
        for (Object loopValue : array){
            if (loopValue == null){
                continue;
            }
            if (loopValue == value){
                return true;
            }
        }
        return false;
    }
    @Override
    public String toString(){
        String result = "[";
        for (int i = 0; i < size(); i++){
            if (array[i] == null){
                continue;
            }
            result += array[i];
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
        return result + "]";
    }

    private void doubleIndexLimit(){
        Object[] save = this.array;
        int size = this.array.length;
        this.array = new Object[size*2];
        for (int i = 0; i < save.length; i++){
            array[i] = save[i];
        }
    }

}
