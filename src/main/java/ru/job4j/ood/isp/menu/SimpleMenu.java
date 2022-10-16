package ru.job4j.ood.isp.menu;

import java.util.*;

public class SimpleMenu implements Menu {

    private final List<MenuItem> rootElements = new ArrayList<>();

    @Override
    public boolean add(String parentName, String childName, ActionDelegate actionDelegate) {
        Optional<ItemInfo> parentItem = findItem(parentName);
        if (findItem(childName).isPresent()) {
            return false;
        }
        if (Objects.equals(Menu.ROOT, parentName)) {
            return rootElements.add(new SimpleMenuItem(childName, actionDelegate));
        } else if (parentItem.isPresent()) {
            return parentItem.get().getMenuItem().getChildren().add(new SimpleMenuItem(childName, actionDelegate));
        }
        return false;
    }

    @Override
    public Optional<MenuItemInfo> select(String itemName) {
        return findItem(itemName).map(item -> new MenuItemInfo(item.menuItem, item.number));
    }

    @Override
    public Iterator<MenuItemInfo> iterator() {
        Iterator<ItemInfo> iterator = new DFSIterator();
        return new Iterator<>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public MenuItemInfo next() {
                ItemInfo item = iterator.next();
                return new MenuItemInfo(item.getMenuItem(), item.getNumber());
            }
        };
    }

    private Optional<ItemInfo> findItem(String name) {
        Optional<ItemInfo> itemInfo = Optional.empty();
        Iterator<ItemInfo> iterator = new DFSIterator();
        while (iterator.hasNext()) {
            ItemInfo item = iterator.next();
            if (item.getMenuItem().getName().equals(name)) {
                itemInfo = Optional.of(item);
                break;
            }
        }
        return itemInfo;
    }

    private static class SimpleMenuItem implements MenuItem {

        private String name;
        private List<MenuItem> children = new ArrayList<>();
        private ActionDelegate actionDelegate;

        public SimpleMenuItem(String name, ActionDelegate actionDelegate) {
            this.name = name;
            this.actionDelegate = actionDelegate;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public List<MenuItem> getChildren() {
            return children;
        }

        @Override
        public ActionDelegate getActionDelegate() {
            return actionDelegate;
        }
    }

    private class DFSIterator implements Iterator<ItemInfo> {

        Deque<MenuItem> stack = new LinkedList<>();

        Deque<String> numbers = new LinkedList<>();

        DFSIterator() {
            int number = 1;
            for (MenuItem item : rootElements) {
                stack.addLast(item);
                numbers.addLast(String.valueOf(number++).concat("."));
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public ItemInfo next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            MenuItem current = stack.removeFirst();
            String lastNumber = numbers.removeFirst();
            List<MenuItem> children = current.getChildren();
            int currentNumber = children.size();
            for (var i = children.listIterator(children.size()); i.hasPrevious();) {
                stack.addFirst(i.previous());
                numbers.addFirst(lastNumber.concat(String.valueOf(currentNumber--)).concat("."));
            }
            return new ItemInfo(current, lastNumber);
        }

    }

    private class ItemInfo {
        MenuItem menuItem;
        String number;

        public ItemInfo(MenuItem menuItem, String number) {
            this.menuItem = menuItem;
            this.number = number;
        }

        public MenuItem getMenuItem() {
            return menuItem;
        }

        public void setMenuItem(MenuItem menuItem) {
            this.menuItem = menuItem;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }
    }
}