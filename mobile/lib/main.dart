import 'package:flutter/material.dart';

void main() {
  runApp(const GreenCorridorApp());
}

class GreenCorridorApp extends StatelessWidget {
  const GreenCorridorApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'GreenCorridor',
      home: Scaffold(
        appBar: AppBar(title: const Text('GreenCorridor')),
        body: const Center(
          child: Text('Every Second Matters. Every Signal Counts.'),
        ),
      ),
    );
  }
}
