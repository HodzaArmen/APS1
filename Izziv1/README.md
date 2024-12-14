# Eksperimentalno določanje časovne zahtevnosti

Napišite program v programskem jeziku Java za empirično primerjavo dveh algoritmov za iskanje elementa v urejeni tabeli:
<ul>
  <li>navadnega zaporednega iskanja in</li>
  <li>dvojiškega iskanja.</li>
</ul>

Oba algoritma poženite večkrat za različne velikosti tabele. Pri tem izmerite in izračunajte povprečni čas iskanja elementa ter izpišite tabelo časov za oba algoritma.
Odgovorite: 
<ul>
  <li>Kateri algoritem je časovno učinkovitejši?</li>
  <li>Kako narašča čas iskanja za oba algoritma v odvisnosti od velikosti tabele?</li>
  <li>Kakšen bi bil idelaen algoritem?</li>
  <li>Katere so težave tovrstnega določanja časovne zahtevnosti?</li>
</ul>

### Predlagamo, da izziv rešujete postopoma po naslednjih točkah.
**a) Generiranje testnih primerov** 
Za generiranje testnih primerov napišite metodo, ki vam za podani n vrne (urejeno) tabelo celih števil z vrednostmi od 1 do n. Npr.
<ul>
  <li>int[] generateTable(int n)</li>
</ul>

**b) Implementacija obeh algoritmov iskanja elementa**
Napišite oba algoritma za iskanje elementa. Npr.

<ul>
  <li>int findLinear(int[] a, int v)</li>
  <li>int findBinary(int[] a, int l, int r, int v)</li>
</ul>
Pri tem je a tabela elementov, v iskana vrednost, l leva meja v tabeli in r desna meja v tabeli. Kakšno vrednost imata l in r ob prvem klicu findBinary(...)?

**c) Izvedba ene meritve za tabelo velikosti n**
Napišite metodi (za vsak način iskanja svojo metodo), ki izmerita povprečni čas iskanja v tabeli velikosti n. Npr.

<ul>
  <li>long timeLinear(int n)</li>
  <li>long timeBinary(int n)</li>
</ul>

Vsaka izmed metod naj izvede naslednje:
<ul>
  <li>Ustvari tabelo velikosti n z metodo, ki ste jo implementirali predhodno.</li>
  <li>Začne meriti čas.</li>
  <li>Nato 1000-krat ponovi naslednje</li>
  <ul>
    <li>Ustvari naključno število med 1 in n.</li>
    <li>Poišče število v tabeli.</li>
  </ul>
  <li>Ustavi merjenje časa.</li>
  <li>Izračuna povprečen čas iskanja števila</li>
</ul>

Čas izvajanja merite na sledeči način:
```
long startTime = System.nanoTime();
// iskanje elementa
long executionTime = System.nanoTime() - startTime;
```

**d) Eksperimentalno ovrednotenje algoritmov**
Za vrednosti n ∈ [20000,...,1000000] s korakom 20000 tabelirajte povprečni čas izvajanja. Izpišite tabelo s tremi stolpci:
<ul>
  <li>prvi stolpec naj vsebuje n,</li>
  <li>drugi povprečni čas izvajanja navadnega iskanja,</li>
  <li>tretji pa povprečni čas dvojiškega iskanja.</li>
</ul>

Primer izpisa:

   n       |     linearno  |   dvojisko  |
---------+--------------+------------------
    20000 |         20662 |           61
    40000 |         21444 |           66
    60000 |         22135 |           66
    80000 |         22706 |           67
    100000 |        23433 |           62
    120000 |        23751 |           71
      ...            ...              ...

**e) Razmislite in odgovorite**
<ul>
  <li>Zakaj so na časi pri vas drugačni kot v zgornji tabeli?</li>
  <li>Kateri algoritem je hitrejši?</li>
  <li>Kdaj bi lahko bil počasnejši algoritem hitrejši?</li>
  <li>Kako se čas iskanja odvisen od velikosti naloge (linearno, kvadratno, ...)?</li>
  <li>Je časovna odvisnost dvojiškega iskanja bližje linearni ali konstantni?</li>
  <li>Ali lahko napišemo boljši algoritem (za naš primer)?</li>
  <li>Katere so težave tovrstnega določanja časovne zahtevnosti?</li>
  <li>Kako jih skušamo zaobiti? </li>
</ul>
