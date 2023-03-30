# task1

ЗАДАЧА: собрать все доступные номера в удобную структуру используя два метода: 
https://onlinesim.ru/api/getFreeCountryList 
https://onlinesim.ru/api/getFreePhoneList?country=? 

Вывести все номера по всем странам в консоль, или файл, или графический интерфейс на Swing или JavaFx. 

Можно использовать любые библиотеки, чем меньше, тем лучше. 

Справка по api: 
https://onlinesim.ru/docs/api/ru/free/_info 

В этом репозитории лежит решение 1 задания. (2 и 3 в редми файле ниже)

#task2

Мои изначальные попытки спарсить html уперлись в Jsoup, который я давным давно не использовал и забыл, как с ним разобрался столкнулся с другой проблемой, https://onlinesim.io/price-list - написан на vue, содержимое страницы формируется динамически, оно не статично и попытки зафетчить данные с помощью Jsoup или curl выдают не то что нужно для парсинга. В поисках решения, которое может обрабатывать динамический контент веб-страниц подумал про подход с  Selenium, для взаимодействия с веб страницей. Но эта реализация не на 3 часа, надеюсь. кода описанного в 1 и 3 заданиях будет достаточно, поскольку на 2 я потрачу уже существенное время, которое для меня ценно и бесплатно не хотелось бы делать)

#task3

public class CommandProcessor {

    private ChannelHandlerContext ctx;

    public CommandProcessor(ChannelHandlerContext ctx) {
        this.ctx = ctx;
    }

    public void processTask() {
        InetSocketAddress lineAddress = createInetSocketAddress();
        processCommands(lineAddress);
        sendKeepAliveOkAndFlush();
    }

    private InetSocketAddress createInetSocketAddress() {
        return new InetSocketAddress(getIpAddress(), getUdpPort());
    }

    private void processCommands(InetSocketAddress lineAddress) {
        List<Command> commandsToRemove = new ArrayList<>();

        for (Command currentCommand : getAllCommands()) {
            if (!currentCommand.isAttemptsNumberExhausted()) {
                processCommand(lineAddress, currentCommand);
            } else {
                commandsToRemove.add(currentCommand);
            }
        }

        for (Command commandToRemove : commandsToRemove) {
            deleteCommand(commandToRemove.getCommandType());
        }
    }

    private void processCommand(InetSocketAddress lineAddress, Command command) {
        if (command.isTimeToSend()) {
            sendCommandToContext(lineAddress, command.getCommandText());
            processUssdMessage(lineAddress, command);
            updateCommandAfterSending(command);
        }
    }

    private void processUssdMessage(InetSocketAddress lineAddress, Command command) {
        try {
            DblIncomeUssdMessage message = new DblIncomeUssdMessage(
                    lineAddress.getHostName(),
                    lineAddress.getPort(),
                    0,
                    EnumGoip.getByModel(getGoipModel()),
                    command.getCommandText());

            AdminController.getInstance().processUssdMessage(message, false);
        } catch (Exception ignored) {
        }
    }
}
        
