from enum import Enum
from typing import List


class PIRField:
    def __eq__(self, other):
        pass

    def __gt__(self, other):
        pass

    def __lt__(self, other):
        pass

    def __contains__(self, item):
        pass


class PIRText(PIRField):
    pass


class PIRTime(PIRField):
    pass


class PIRFactory:
    def get_inputs(self):
        pass


class ContactPIRFactory(PIRFactory):
    def get_inputs(self):
        pass


class PIR:
    factory: PIRFactory = None

    def __init__(self):
        self.attributes: List[PIRField] = []

    def __str__(self) -> str:
        pass


class PlainTextPIR(PIR):
    def __init__(self, text: PIRText):
        super(PlainTextPIR, self).__init__()

        self.attributes.append(text)


class TaskPIR(PIR):
    pass


class EventPIR(PIR):
    pass


class ContactPIR(PIR):
    factory = ContactPIRFactory()


class PIRDatabase:
    def __init__(self):
        self.repository: List[PIR] = []

    def add(self, pir: PIR):
        pass


class RawCommand:
    def __init__(self, raw_command_text: str):
        self.raw_command_text = raw_command_text
        self.pieces = self.raw_command_text.split(' ')

    def get_name(self):
        return self.pieces[0]

    def get_pir_type(self):
        return self.pieces[1]

    # ...


class Command:
    ADD = 'add'

    def __init__(self, raw_command: RawCommand):
        self.name = raw_command.get_name()
        self.pir_type = raw_command.get_pir_type()

    def is_add_pir(self) -> bool:
        return self.name == Command.ADD


class Commands(Enum):
    ADD = 'add'

    CONTACT_TYPE = 'contact'


class App:
    def __init__(self, _pir_database: PIRDatabase):
        self.pir_database = _pir_database

    def run(self):
        print("---\nWelcome & User docs\n---\n")
        print("Available commands: \nADD\nSEARCH\n...\n")

        while True:
            command = str(input("Type in your command: "))
            if command == Commands.ADD:
                pass
            # ...

            command_type = str(input("Insert the type of PIR: "))

            if command_type == Commands.CONTACT_TYPE:
                pass
            # ...


if __name__ == '__main__':
    pir_database = PIRDatabase()
    app = App(pir_database)
    app.run()
